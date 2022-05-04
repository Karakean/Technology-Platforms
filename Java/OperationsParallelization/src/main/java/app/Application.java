package app;
import org.apache.commons.lang3.tuple.Pair;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Application {

    private static final int MAX_THREADS_NUMBER = 15;


    public static void main(String[] args) {

        for(int i = 1; i <= MAX_THREADS_NUMBER; i++){
            System.out.print("Number of threads: " + i);
            try {
                function(i, "src/main/resources/srcImages", "src/main/resources/dstImages");
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private static void function(int threadsNumber, String srcPath, String dstPath) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool(threadsNumber);
        long start = System.currentTimeMillis();
        forkJoinPool.submit(() -> {
            try {
                Path src = Path.of(srcPath);
                List<Path> files = new ArrayList<>();
                try (Stream<Path> stream = Files.list(src)) {
                    files = stream.parallel().collect(Collectors.toList());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                List<Pair<Path, BufferedImage>> pairList;
                pairList = files.stream().parallel().map(path -> {
                    BufferedImage bufferedImage = null;
                    try {
                        BufferedImage srcImage = ImageIO.read(path.toFile());
                        bufferedImage = new BufferedImage(
                                srcImage.getWidth(),
                                srcImage.getHeight(),
                                srcImage.getType()
                        );
                        for (int i = 0; i < bufferedImage.getHeight(); i++) {
                            for (int j = 0; j < bufferedImage.getWidth(); j++) {
                                bufferedImage.setRGB(j, i, srcImage.getRGB(j, i));
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return Pair.of(path, bufferedImage);
                }).collect(Collectors.toList());
                pairList.stream().parallel().map(pair -> {
                    BufferedImage bufferedImage = pair.getRight();
                    for (int i = 0; i < bufferedImage.getHeight(); i++) {
                        for (int j = 0; j < bufferedImage.getWidth(); j++) {
                            Color color = new Color(bufferedImage.getRGB(j, i));
                            int red = color.getRed();
                            int blue = color.getBlue();
                            int green = color.getGreen();
                            Color dstColor = new Color(blue, green, red);
                            bufferedImage.setRGB(j, i, dstColor.getRGB());
                        }
                    }
                    String filename = pair.getLeft().getFileName().toString();
                    return Pair.of(Path.of(dstPath + "/" + filename), bufferedImage);
                }).forEach(pair -> {
                    try {
                        ImageIO.write(pair.getRight(),"jpg", pair.getLeft().toFile());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e){
                e.printStackTrace();
            }
        }).get();
        System.out.println(", elapsed time: " + (double)(System.currentTimeMillis() - start) / 1000 + "s");
    }
}
