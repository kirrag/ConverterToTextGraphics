package ru.netology.graphics;

import ru.netology.graphics.image.TextGraphicsConverter;
import ru.netology.graphics.server.GServer;
import ru.netology.graphics.image.Converter;

public class Main {
    public static void main(String[] args) throws Exception {
        TextGraphicsConverter converter = new Converter(); // Создайте тут объект вашего класса конвертера
        converter.setMaxWidth(100);
        converter.setMaxHeight(100);
        converter.setMaxRatio(4);
        //String url = "https://raw.githubusercontent.com/netology-code/java-diplom/main/pics/simple-test.png";
        //String url = "https://i.ibb.co/6DYM05G/edu0.jpg";
        //String url = "https://cdn-0.enacademic.com/pictures/enwiki/85/Uniform_polyhedron-33-t0.png";
        //String url = "https://s3.wi-fi.ru/cp3o/ko0dwcfahuxqf7fysxctwsx8oxuz?response-content-type=image%2Fjpeg";
        //String url = "file:///home/garrik/DSCN0958.jpg";
        //String imgTxt = converter.convert(url);
        //System.out.println(imgTxt);

        GServer server = new GServer(converter); // Создаём объект сервера
        server.start(); // Запускаем
    }
}
