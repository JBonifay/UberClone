package com.joffrey.uberclone.unit.usecases.bookride;

import com.joffrey.uberclone.businesslogic.models.Block;
import com.joffrey.uberclone.businesslogic.models.BlockType;
import com.joffrey.uberclone.businesslogic.usecases.bookride.BookRideUseCase;
import com.joffrey.uberclone.businesslogic.usecases.bookride.csv.CsvReader;
import com.joffrey.uberclone.businesslogic.usecases.bookride.csv.FakeCsvReader;
import com.joffrey.uberclone.businesslogic.usecases.bookride.csv.FileCsvReader;
import org.junit.jupiter.api.Test;

import static com.joffrey.uberclone.businesslogic.models.BlockType.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class BookRideUseCaseTest {
    public static final String FAKE_FILE = null;
    public static final String INIT_CSV_FILE = "test-init.csv";
    private final BookRideUseCase bookRideUseCase = new BookRideUseCase();
    private final BlockType R = ROAD;
    private final BlockType B = BUILDING;
    private final BlockType I = RIVER;
    private final BlockType P = PARK;

    @Test
    void shouldGenerateBuildingsFromInput() {
        assertMapWasGenerated(new BlockType[][]{
                {R, R, R, R, R},
                {R, R, R, R, R},
                {R, R, R, R, R},
                {R, R, R, R, R},
                {R, R, R, R, R}
        });

        assertMapWasGenerated(new BlockType[][]{
                {R, R, R, R, R},
                {R, B, B, B, R},
                {R, R, R, R, R},
                {R, R, R, R, R},
                {R, R, R, R, R}
        }, new Block(BUILDING, 1, 3, 1, 1, BUILDING.getColor()));

        assertMapWasGenerated(new BlockType[][]{
                {R, R, R, R, R},
                {R, B, B, B, R},
                {R, B, B, B, R},
                {R, R, R, R, R},
                {R, R, R, R, R}
        }, new Block(BUILDING, 1, 3, 1, 2, BUILDING.getColor()));

        assertMapWasGenerated(new BlockType[][]{
                        {R, R, R, R, R},
                        {R, B, B, B, R},
                        {R, B, B, B, R},
                        {R, R, R, B, R},
                        {B, R, R, R, R}
                },
                new Block(BUILDING, 1, 3, 1, 2, BUILDING.getColor()),
                new Block(BUILDING, 3, 3, 3, 3, BUILDING.getColor()),
                new Block(BUILDING, 0, 0, 4, 4, BUILDING.getColor())
        );
    }

    @Test
    void shouldGenerateMapAndFillItFromCsvSource() {
        final FakeCsvReader fakeCsvReader = new FakeCsvReader(new String[][]{
                {"building", "1", "2", "1", "2", BUILDING.getColor()},
                {"building", "1", "1", "3", "3", BUILDING.getColor()},
                {"building", "4", "5", "1", "3", BUILDING.getColor()},
                {"building", "6", "6", "2", "3", BUILDING.getColor()},
                {"building", "8", "8", "1", "1", BUILDING.getColor()},
                {"building", "8", "8", "3", "3", BUILDING.getColor()},
                {"building", "1", "1", "5", "5", BUILDING.getColor()},
                {"building", "3", "3", "5", "5", BUILDING.getColor()},
                {"building", "5", "5", "5", "5", BUILDING.getColor()},
                {"building", "6", "8", "5", "6", BUILDING.getColor()},
                {"building", "7", "8", "7", "7", BUILDING.getColor()},
                {"building", "7", "7", "8", "8", BUILDING.getColor()},
                {"building", "1", "1", "7", "8", BUILDING.getColor()},
                {"building", "3", "4", "7", "8", BUILDING.getColor()},
                {"building", "5", "5", "8", "8", BUILDING.getColor()}
        });
        assertMapWasGenerated(new BlockType[][]{
                        {R, R, R, R, R, R, R, R, R, R},
                        {R, B, B, R, B, B, R, R, B, R},
                        {R, B, B, R, B, B, B, R, R, R},
                        {R, B, R, R, B, B, B, R, B, R},
                        {R, R, R, R, R, R, R, R, R, R},
                        {R, B, R, B, R, B, B, B, B, R},
                        {R, R, R, R, R, R, B, B, B, R},
                        {R, B, R, B, B, R, R, B, B, R},
                        {R, B, R, B, B, B, R, B, R, R},
                        {R, R, R, R, R, R, R, R, R, R}},
                fakeCsvReader,
                FAKE_FILE);

        assertMapWasGenerated(new BlockType[][]{
                        {R, R, R, R, R, R, R, R, R, R},
                        {R, B, B, R, B, B, R, R, B, R},
                        {R, B, B, R, B, B, B, R, R, R},
                        {R, B, R, R, B, B, B, R, B, R},
                        {R, R, R, R, R, R, R, R, R, R},
                        {R, B, R, B, R, B, B, B, B, R},
                        {R, R, R, R, R, R, B, B, B, R},
                        {R, B, R, B, B, R, R, B, B, R},
                        {R, B, R, B, B, B, R, B, R, R},
                        {R, R, R, R, R, R, R, R, R, R}
                },
                new FileCsvReader(),
                INIT_CSV_FILE);
    }

    @Test
    void mapShouldContainsDifferentBlockTypes() {
        FakeCsvReader fakeCsvReader = new FakeCsvReader(new String[][]{
                {"river", "0", "1", "0", "0", RIVER.getColor()},
                {"river", "1", "1", "0", "3", RIVER.getColor()},
                {"river", "3", "3", "3", "3", RIVER.getColor()},
                {"river", "3", "4", "2", "2", RIVER.getColor()},
                {"park", "3", "4", "0", "1", PARK.getColor()}
        });
        var csvContent = fakeCsvReader.readFile(FAKE_FILE);

        bookRideUseCase.generateMap(5, 5);
        bookRideUseCase.generateBlocksFromCsvInput(csvContent);

        assertArrayEquals(new BlockType[][]{
                        {I, I, R, P, P},
                        {R, I, R, P, P},
                        {R, I, R, I, I},
                        {R, I, R, I, R},
                        {R, R, R, R, R}
                }, bookRideUseCase.map(),
                displayMap(bookRideUseCase.map()));
    }

    void assertMapWasGenerated(final BlockType[][] expected, final Block... blocks) {
        bookRideUseCase.generateMap(5, 5);
        bookRideUseCase.generateBlocks(blocks);
        assertArrayEquals(expected, bookRideUseCase.map(), displayMap(bookRideUseCase.map()));
    }

    void assertMapWasGenerated(final BlockType[][] expected, final CsvReader csvReader, final String fileName) {
        var csvContent = csvReader.readFile(fileName);

        bookRideUseCase.generateMap(10, 10);
        bookRideUseCase.generateBlocksFromCsvInput(csvContent);

        assertArrayEquals(expected, bookRideUseCase.map(), displayMap(bookRideUseCase.map()));
    }

    String displayMap(final BlockType[][] map) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");

        for (final BlockType[] blockTypes : map) {
            for (final BlockType blockType : blockTypes) {
                sb.append(blockType.shortRepresentation()).append(" ");
            }
            sb.append("\n");
        }
        sb.append("\n");

        return sb.toString();
    }
}