package com.bakrabros.android.puzzle.sample;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.bakrabros.android.puzzle.channel.Channel;
import com.bakrabros.android.puzzle.generator.ThreeSlotPuzzleGenerator;
import com.bakrabros.android.puzzle.manager.ThreeSlotPuzzleManagerImpl;
import com.bakrabros.android.puzzle.model.Puzzle;
import com.bakrabros.android.puzzle.util.java8.Consumer;
import com.bakrabros.android.puzzle.view.PuzzleView;

import static com.bakrabros.android.puzzle.constant.PuzzleEvents.PUZZLE_COMPLETED;
import static com.bakrabros.android.puzzle.constant.PuzzleEvents.SHOW_NEXT_PUZZLE_PIECE;

public class MainActivity extends Activity {

    /**
     * The puzzle view in this activity's view
     */
    private PuzzleView puzzleView;

    private Channel channel;

    private ThreeSlotPuzzleGenerator threeSlotPuzzleGenerator;

    private Puzzle currentPuzzle;

    private ThreeSlotPuzzleManagerImpl puzzleManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.puzzleView = this.findViewById(R.id.puzzleView);

        this.channel = new Channel();

        this.puzzleView.post(new Runnable() {
            @Override
            public void run() {
                threeSlotPuzzleGenerator = new ThreeSlotPuzzleGenerator(
                        getApplicationContext(),
                        "data.json",
                        puzzleView.getWidth(),
                        puzzleView.getHeight());

                startNextPuzzleGame();
            }
        });

        this.channel.subscribe(PUZZLE_COMPLETED, new Consumer<View>() {
            @Override
            public void accept(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Puzzle Completed!!")
                        .setMessage("Replay?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                startNextPuzzleGame();
                            }
                        }).show();
            }
        });
    }

    private void startNextPuzzleGame() {
        currentPuzzle = threeSlotPuzzleGenerator.generateNewPuzzle();
        puzzleManager = new ThreeSlotPuzzleManagerImpl(channel, currentPuzzle, getResources().getInteger(R.integer.snap_distance));
        puzzleView.setPuzzleManager(puzzleManager);
        channel.emit(SHOW_NEXT_PUZZLE_PIECE);
        puzzleView.invalidate();
    }
}
