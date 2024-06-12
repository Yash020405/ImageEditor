import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
//import java.util.Arrays;
import java.util.Scanner;
import javax.imageio.ImageIO;
import java.awt.*;
public class ImageEditor {
    public static BufferedImage convertToGrayScale(BufferedImage inputImage) {
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(width, height,BufferedImage.TYPE_BYTE_GRAY);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                outputImage.setRGB(j, i, inputImage.getRGB(j, i));
            }
        }
        return outputImage;
    }
    public static BufferedImage colorInvert(BufferedImage inputImage) {
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color pixel = new Color(inputImage.getRGB(j, i));
                int red = pixel.getRed();
                int blue = pixel.getBlue();
                int green = pixel.getGreen();
                int finalred = 255 - red;
                int finalblue = 255 -blue;
                int finalgreen = 255- green;
                Color newPixel = new Color (finalred,finalgreen,finalblue);
                outputImage.setRGB(j, i, newPixel.getRGB());
            }
        }
        return outputImage;
    }
    public static BufferedImage rotateImage(BufferedImage inputImage) {
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(height, width,BufferedImage.TYPE_INT_RGB);
        int r=0;
        for (int i = 0; i < width; i++) {
            int c=0;
            for (int j = height-1; j>=0; j--) {
                Color pixel = new Color(inputImage.getRGB(i, j));
                outputImage.setRGB(c, r, pixel.getRGB());
                c++;
            }r++;
        }return outputImage;
    }
    public static BufferedImage horizontalSwitch(BufferedImage inputImage) {
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        int r=0;
        for (int i=0; i<height; i++) {
            int c=0;
            for (int j = width-1; j>=0; j--) {
                Color pixel = new Color(inputImage.getRGB(j, i));
                outputImage.setRGB(c, r, pixel.getRGB());
                c++;
            }r++;
        }return outputImage;
    }
    public static BufferedImage verticalSwitch(BufferedImage inputImage) {
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        int r=0;
        for (int i=height-1; i>=0; i--) {
            int c=0;
            for (int j = 0; j<width; j++) {
                Color pixel = new Color(inputImage.getRGB(j, i));
                outputImage.setRGB(c, r, pixel.getRGB());
                c++;
            }r++;
        }return outputImage;
    }
    public static void blurSquare(BufferedImage inputImage, BufferedImage outputImage, int start_row, int end_row, int start_col, int end_col){
            int r=0,b=0,g=0,c=0;
            for(int i=start_row;i< end_row;i++){
                for(int j=start_col;j<end_col;j++){
                    Color pixel = new Color (inputImage.getRGB(j, i));
                    r+=pixel.getRed();
                    b+=pixel.getBlue();
                    g+=pixel.getGreen();
                    c++;
                }
            }
            Color newPixel = new Color(r/c,b/c,g/c);
            for(int k=start_row;k<end_row;k++){
                for(int u=start_col;u<end_col;u++){
                    outputImage.setRGB(u, k, newPixel.getRGB());
                }
            }
    }
    public static BufferedImage blurImage(BufferedImage inputImage, int blur) {
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        for(int row=0;row+blur<height;row+=blur){
            for(int col=0;col+blur<width;col+=blur){
                blurSquare(inputImage,outputImage,row,row+blur,col,col+blur);
            }
        }if ((height*width)%(blur*blur)!=0){
            
        }
        return outputImage;
    }
    
        public static BufferedImage changeBrightness(BufferedImage inputImage,int increase) {
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(width, height,BufferedImage.TYPE_3BYTE_BGR);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color pixel = new Color(inputImage.getRGB(j, i));
                int red = pixel.getRed();
                int blue = pixel.getBlue();
                int green = pixel.getGreen();
                red = red + (increase * red / 100);
                blue = blue + (increase * blue) / 100;
                green = green + (increase * green) / 100;
                if (red > 255) red = 255;
                if (blue > 255) blue = 255;
                if (green > 255) green = 255;
                if (red < 0) red = 0;
                if (blue < 0) blue = 0;
                if (green < 0) green = 0;
                Color newPixel = new Color(red, green, blue);
                outputImage.setRGB(j, i, newPixel.getRGB());
            }
        }return outputImage;
    }
    public static void main(String args[]) {
        System.out.println();
        System.out.println("WELCOME!!");
        Scanner sc = new Scanner (System.in);
        System.out.print("Enter the path of the image ::");
        String s = sc.nextLine();
        boolean ch = true;
        try {
            while(ch){
                System.out.println();
                System.out.print("Enter the path to save the image ::");
                String p;
                p = sc.next();
                String o = "";
                if(p.length()>4){
                    for (int i=p.length()-4;i<p.length();i++){
                        o+=p.charAt(i);
                    }
                    if (o.equals(".jpg")){
                        p=p+"";
                    }else{
                        p=p+".jpg";
                    }
                }else{
                    p=p+".jpg";
                }
                System.out.println();
                System.out.println("Enter 1 to convert the image to grayscale.");
                System.out.println("Enter 2 to change the brightness.");
                System.out.println("Enter 3 to rotate the image clockwise.");
                System.out.println("Enter 4 to rotate the image anti-clockwise.");
                System.out.println("Enter 5 to horizontally flip the image.");
                System.out.println("Enter 6 to vertically flip the image.");
                System.out.println("Enter 7 to blur the image.");
                System.out.println("Enter 8 to invert the image.");
                System.out.println("Enter 0 to exit.");
                System.out.print("Enter the choice::");
                int choice = sc.nextInt();
                File inputFile = new File(s);
                BufferedImage inputImage = ImageIO.read(inputFile);
                if (choice==1){
                    BufferedImage grayScale = convertToGrayScale(inputImage);
                    File graScaleImage = new File(p);
                    ImageIO.write(grayScale, "jpg", graScaleImage);
                    System.out.println("Image changed to GrayScale Successfully!!");
                }else if (choice==2){
                    System.out.print("Enter the brightness value to be modified::");
                    int N = sc.nextInt();
                    BufferedImage changedBrightness = changeBrightness(inputImage,N);
                    File changedBrightnessImage = new File(p);
                    ImageIO.write(changedBrightness, "jpg", changedBrightnessImage);
                    System.out.println("Image Brightness Changed Successfully!!");
                }else if (choice ==3){
                    BufferedImage rotatedImageC = rotateImage(inputImage);
                    File RotatedImageC = new File(p);
                    ImageIO.write(rotatedImageC, "jpg", RotatedImageC);
                    System.out.println("Image Rotated clockwise Successfully!!");
                }else if (choice==4){
                    BufferedImage rotatedImageAC = verticalSwitch(horizontalSwitch(rotateImage(inputImage)));
                    File RotatedImageAC = new File(p);
                    ImageIO.write(rotatedImageAC, "jpg", RotatedImageAC);
                    System.out.println("Image Rotated anti-clockwise Successfully!!");
                }else if (choice ==5){
                    BufferedImage HoriImage = horizontalSwitch(inputImage);
                    File HorizontalImage = new File(p);
                    ImageIO.write(HoriImage, "jpg", HorizontalImage);
                    System.out.println("Image Flipped Horizontally Successfully!!");
                }else if (choice ==6){
                    BufferedImage VertiImage = verticalSwitch(inputImage);
                    File VerticalImage = new File(p);
                    ImageIO.write(VertiImage, "jpg", VerticalImage);
                    System.out.println("Image Flipped Vertically Successfully!!");
                }else if (choice ==7){
                    System.out.print("Enter the Blurring value::");
                    int N = sc.nextInt();
                    if (N>inputImage.getHeight() && N>inputImage.getWidth()){
                        if(inputImage.getHeight()>inputImage.getWidth()){
                            N=inputImage.getWidth();
                        }else{
                            N=inputImage.getHeight();
                        }
                    }else if(N>inputImage.getHeight()){
                        N=inputImage.getHeight();
                    }else if (N>inputImage.getWidth()){
                        N=inputImage.getWidth();
                    }else if (N<0){
                        System.out.println("Invalid input !!");
                        break;
                    }
                    BufferedImage BlurImage = blurImage(inputImage,N);
                    File BlooImage = new File(p);
                    ImageIO.write(BlurImage, "jpg", BlooImage);
                    System.out.println("Image Blurred Successfully!!");
                }else if (choice ==8){
                    BufferedImage InvertImage = colorInvert(inputImage);
                    File InvertedImage = new File(p);
                    ImageIO.write(InvertImage, "jpg", InvertedImage);
                    System.out.println("Image Inverted Successfully!!");
                }
                else if(choice==0){
                    ch=false;
                    System.out.println("Thank You for visiting!!");
                    break;
                }else{
                    System.out.println("Invalid choice!!");
                }
                System.out.println();
                System.out.print("Do you want to continue?(Enter Y to continue)");
                char cont = sc.next().charAt(0);
                char yes = 'Y';
                if (cont != yes && ch){
                    ch=false;
                    System.out.println("Thank You for visiting!!");
                    break;
                }else{
                    System.out.println("Enter 9 to work on the modified image");
                    System.out.println("Enter 10 to work on original image.");
                    System.out.println("Enter 11 to work on new image.");
                }
                System.out.print("Enter the choice::");
                int K = sc.nextInt();
                if (K==9){
                    s=""+p;
                }else if (K==11){
                    String t="";
                    System.out.print("Enter the path of the image::");
                    t=sc.next();
                    s=t;
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}