package com.bakrabros.android.puzzle.parser.model;

import java.util.List;

/**
 * @author BakraBros
 */
public class PuzzleDto {

    private List<PuzzlePieceDto> pieces;

    public List<PuzzlePieceDto> getPieces() {
        return pieces;
    }

    public void setPieces(List<PuzzlePieceDto> pieces) {
        this.pieces = pieces;
    }
}
