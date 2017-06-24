package com.bakrabros.android.puzzle.parser;

import android.content.Context;

import com.bakrabros.android.puzzle.parser.model.PuzzleDto;
import com.bakrabros.android.puzzle.util.AssetUtils;
import com.bakrabros.android.puzzle.util.ObjectUtils;
import com.google.gson.Gson;

/**
 * @author BakraBros
 */
public class PuzzleParser {

    public static PuzzleDto parse(final Context context, final String filePath) {
        ObjectUtils.requireNonNull(context, "context is null");
        ObjectUtils.requireNonNull(filePath, "filePath is null");

        final Gson gson = new Gson();

        return gson.fromJson(AssetUtils.read(context, filePath), PuzzleDto.class);
    }
}
