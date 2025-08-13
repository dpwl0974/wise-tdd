package com.back.standard.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

//파일 관련
public class Util {

    // 이너 클래스
    public static class file {

        private static Path getPath(String filePath) {
            return Paths.get(filePath);
        }

        public static void touch(String filePath) {
            set(filePath, "");
        }

        public static void set(String filePath, String content) {
            Path path = getPath(filePath);
            try {
                writeFile(path, content);
            } catch (IOException e) {
                handleFileWriteError(path, content, e);
            }
        }

        private static void writeFile(Path path, String content) throws IOException {
            Files.writeString(path, content,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        }

        private static void handleFileWriteError(Path path, String content, IOException e) {

        }

        public static boolean exists(String filePath) {
            return Files.exists(getPath(filePath));
        }

        public static void delete(String filePath) {
        }
    }

    public static class json {

    }

}