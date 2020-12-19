package com.animsh.pokedex.model;

import com.google.gson.annotations.SerializedName;

public class Versions {

    @SerializedName("generation-i")
    private Generation1 generation1;

    @SerializedName("generation-ii")
    private Generation2 generation2;

    @SerializedName("generation-iii")
    private Generation3 generation3;

    @SerializedName("generation-iv")
    private Generation4 generation4;

    @SerializedName("generation-v")
    private Generation5 generation5;

    @SerializedName("generation-vi")
    private Generation6 generation6;

    @SerializedName("generation-vii")
    private Generation7 generation7;

    @SerializedName("generation-viii")
    private Generation8 generation8;

    public Versions(Generation1 generation1, Generation2 generation2, Generation3 generation3, Generation4 generation4, Generation5 generation5, Generation6 generation6, Generation7 generation7, Generation8 generation8) {
        this.generation1 = generation1;
        this.generation2 = generation2;
        this.generation3 = generation3;
        this.generation4 = generation4;
        this.generation5 = generation5;
        this.generation6 = generation6;
        this.generation7 = generation7;
        this.generation8 = generation8;
    }

    public Generation1 getGeneration1() {
        return generation1;
    }

    public Generation2 getGeneration2() {
        return generation2;
    }

    public Generation3 getGeneration3() {
        return generation3;
    }

    public Generation4 getGeneration4() {
        return generation4;
    }

    public Generation5 getGeneration5() {
        return generation5;
    }

    public Generation6 getGeneration6() {
        return generation6;
    }

    public Generation7 getGeneration7() {
        return generation7;
    }

    public Generation8 getGeneration8() {
        return generation8;
    }
}
