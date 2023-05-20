package ru.netology.graphics.image;

public class Schema implements TextColorSchema {

    private static boolean isWindows() {
        String os = System.getProperty("os.name").toLowerCase();
        return (os.indexOf("win") >= 0);
    }

    @Override
    public char convert(int color) {
        char c = ' ';
        if (isWindows()) {
            // '#', '$', '@', '%', '*', '+', '-', '''.
            if (color >= 0 && color < 32) {
                c = '#';
            }
            if (color >= 32 && color < 64) {
                c = '$';
            }
            if (color >= 64 && color < 96) {
                c = '@';
            }
            if (color >= 96 && color < 128) {
                c = '%';
            }
            if (color >= 128 && color < 160) {
                c = '*';
            }
            if (color >= 160 && color < 192) {
                c = '+';
            }
            if (color >= 192 && color < 224) {
                c = '-';
            }
            if (color >= 224 && color < 256) {
                c = '\'';
            }
        } else {
            // '▇', '●', '◉', '◍', '◎', '○', '☉', '◌', '-'.
            if (color >= 0 && color < 28) {
                c = '▇';
            }
            if (color >= 28 && color < 56) {
                c = '●';
            }
            if (color >= 56 && color < 84) {
                c = '◉';
            }
            if (color >= 84 && color < 112) {
                c = '◍';
            }
            if (color >= 112 && color < 140) {
                c = '◎';
            }
            if (color >= 140 && color < 168) {
                c = '○';
            }
            if (color >= 168 && color < 196) {
                c = '☉';
            }
            if (color >= 196 && color < 224) {
                c = '◌';
            }
            if (color >= 224 && color < 256) {
                c = '-';
            }
        }
        return c;
    }
}
