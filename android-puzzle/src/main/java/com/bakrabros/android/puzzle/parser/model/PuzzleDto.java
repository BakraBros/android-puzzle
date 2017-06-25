package com.bakrabros.android.puzzle.parser.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author BakraBros
 */
public class PuzzleDto {

    @SerializedName("pieces")
    private List<PuzzlePieceDto> pieces;

    public List<PuzzlePieceDto> getPieces() {
        return pieces;
    }

    public void setPieces(List<PuzzlePieceDto> pieces) {
        this.pieces = pieces;
    }
}
