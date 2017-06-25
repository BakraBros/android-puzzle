package com.bakrabros.android.puzzle.parser.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author BakraBros
 */
public class PuzzlePieceDto {

    @SerializedName("name")
    private String name;

    @SerializedName("puzzlePiecePath")
    private String puzzlePiecePath;

    @SerializedName("slotPath")
    private String slotPath;

    @SerializedName("soundPath")
    private String soundPath;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPuzzlePiecePath() {
        return puzzlePiecePath;
    }

    public void setPuzzlePiecePath(String puzzlePiecePath) {
        this.puzzlePiecePath = puzzlePiecePath;
    }

    public String getSlotPath() {
        return slotPath;
    }

    public void setSlotPath(String slotPath) {
        this.slotPath = slotPath;
    }

    public String getSoundPath() {
        return soundPath;
    }

    public void setSoundPath(String soundPath) {
        this.soundPath = soundPath;
    }
}
