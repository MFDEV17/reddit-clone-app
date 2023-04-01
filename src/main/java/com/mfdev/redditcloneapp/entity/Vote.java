package com.mfdev.redditcloneapp.entity;

public enum Vote {
    UPPER_VOTE(1), LOWER_VOTE(-1);

    private final int voteVal;

    Vote(int voteVal) {
        this.voteVal = voteVal;
    }

    public int getVoteVal() {
        return voteVal;
    }
}
