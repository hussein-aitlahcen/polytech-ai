package com.polytech.app5.fousfous.play;

import com.polytech.app5.fousfous.Board;

public interface Play {
    void apply(final Board board);

    void unapply(final Board board);
}