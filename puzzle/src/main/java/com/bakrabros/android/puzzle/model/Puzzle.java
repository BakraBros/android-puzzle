package com.bakrabros.android.puzzle.model;

import com.bakrabros.android.puzzle.constant.SpriteStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author BakraBros
 */
public class Puzzle {

    private List<PuzzlePiece> pieces;

    private List<BitmapSprite> slots;

    {
        pieces = new ArrayList<>();
        slots = new ArrayList<>();
    }

    public List<PuzzlePiece> getPieces() {
        return pieces;
    }

    public void setPieces(List<PuzzlePiece> pieces) {
        this.pieces = pieces;
    }

    public List<BitmapSprite> getSlots() {
        return slots;
    }

    public void setSlots(List<BitmapSprite> slots) {
        this.slots = slots;
    }

    public void addPiece(PuzzlePiece puzzlePiece) {
        pieces.add(puzzlePiece);
    }

    public void addSlot(BitmapSprite bitmapSprite) {
        slots.add(bitmapSprite);
    }

    public void shuffle() {
        Collections.shuffle(pieces, new Random());
        Collections.shuffle(slots, new Random());
    }

    public void setFirstPieceVisible() {
        for (PuzzlePiece piece : pieces) {
            if (piece.getStatus() == SpriteStatus.HIDDEN) {
                piece.setStatus(SpriteStatus.VISIBLE);
                break;
            }
        }
    }
}
