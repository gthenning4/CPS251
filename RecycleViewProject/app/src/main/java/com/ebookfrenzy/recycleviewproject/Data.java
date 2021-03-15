package com.ebookfrenzy.recycleviewproject;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Data {
    private String[] titles = {"Chapter One",
            "Chapter Two",
            "Chapter Three",
            "Chapter Four",
            "Chapter Five",
            "Chapter Six",
            "Chapter Seven",
            "Chapter Eight"};
    private String[] details = {"Item one details",
            "Item two details", "Item three details",
            "Item four details", "Item five details",
            "Item six details", "Item seven details",
            "Item eight details"};
    private int[] images = {
            R.drawable.bears,
            R.drawable.bengals,
            R.drawable.bills,
            R.drawable.browns,
            R.drawable.broncos,
            R.drawable.cardinals,
            R.drawable.colts,
            R.drawable.cowboys,
            R.drawable.falcons,
            R.drawable.lions,
            R.drawable.packers,
            R.drawable.ravens,
            R.drawable.texans};

    public int randomNum(int min, int max){
            int r = new Random().nextInt(max);
            return r;
    }
    public String getRandomTitle(){
        int arrLength = titles.length;
        int index = randomNum(0,arrLength);
        String randTitle = titles[index];
        return randTitle;
    }
    public String getRandomDetails(){
        int arrLength = details.length;
        int index = randomNum(0,arrLength);
        String randDetail = details[index];
        return randDetail;
    }
    public int getRandomImage(){
        int arrLength = images.length;
        int index = randomNum(0,arrLength);
        int imageTitle = images[index];
        return imageTitle;
    }
    public int getTitleCt(){
        return titles.length;
    }
}
