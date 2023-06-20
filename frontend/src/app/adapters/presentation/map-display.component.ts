import {Component, OnInit, Renderer2} from '@angular/core';
import {Block} from "../../businesslogic/models/block";
import {MapManagementUseCase} from "../../businesslogic/usecases/map-management-use-case";

@Component({
  selector: 'app-map-display',
  templateUrl: './map-display.component.html',
  styleUrls: ['./map-display.component.scss']
})
export class MapDisplayComponent implements OnInit {

  private svgViewSize = 500;
  private gridCount = 0;
  private squareSize = 0;

  constructor(private renderer: Renderer2, private mapManagement: MapManagementUseCase) {
  }

  ngOnInit() {
    let svg = document.getElementById('editorSvg')!;
    svg.setAttribute('width', `${this.svgViewSize}`)
    svg.setAttribute('height', `${this.svgViewSize}`)

    this.mapManagement.fetchMap().subscribe(value => {
      this.gridCount = value.gridCount;
      this.squareSize = this.svgViewSize / this.gridCount;
      this.drawRoad(svg);
      this.drawObstacles(svg, value.blocks);
    });
  }

  drawRoad(svg: HTMLElement) {
    for (let i = 0; i < this.gridCount; i++) {
      for (let j = 0; j < this.gridCount; j++) {
        this.createSvgBlock(j, i, '#eeeeee', svg);
      }
    }
  }

  private drawObstacles(svg: HTMLElement, map: Block[]) {
    map.forEach(block => {
      let x = block.xStart;
      let xEnd = block.xEnd;
      let yStart = block.yStart;
      let yEnd = block.yEnd;

      while (x <= xEnd) {
        let y = yStart
        while (y <= yEnd) {
          this.createSvgBlock(x, y, block.color, svg)
          y++;
        }
        x++;
      }
    })
  }

  private createSvgBlock(x: number, y: number, color: string, parentSvg: HTMLElement) {
    const rect = this.renderer.createElement("rect", 'svg');
    this.renderer.setAttribute(rect, "width", `${this.squareSize}`);
    this.renderer.setAttribute(rect, "height", `${this.squareSize}`);
    this.renderer.setAttribute(rect, "x", `${x * this.squareSize}`);
    this.renderer.setAttribute(rect, "y", `${y * this.squareSize}`);
    this.renderer.setAttribute(rect, 'fill', color);
    this.renderer.appendChild(parentSvg, rect)
  }
}
