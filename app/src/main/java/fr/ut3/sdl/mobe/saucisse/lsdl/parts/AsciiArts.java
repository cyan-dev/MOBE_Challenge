package fr.ut3.sdl.mobe.saucisse.lsdl.parts;

public class AsciiArts {

    public static String healing(int state) {
        String ascii = "                     " + "\n";
        switch (state) {
            case 2:
                ascii +=
                    "      ;     XXXXXXX  " + "\n" +
                    "  -     .   X  o  X  " + "\n" +
                    "   .        XXXo- X  " + "\n" +
                    "     ~  ¨    XX  X  " + "\n" +
                    " '       ;     XXXX  " + "\n";
                break;

            case 1:
                ascii +=
                    "      ;   XXXXXXXXX  " + "\n" +
                    "  -     XXXo   o  X  " + "\n" +
                    "   .   XX. -^  o- X  " + "\n" +
                    "      XXo ~o. ^-  X  " + "\n" +
                    " '   XXXXXXXXXXXXXX  " + "\n";
                break;

            case 0:
            default:
                ascii +=
                    "  XXXXXXXXXXXXXXXXX  " + "\n" +
                    "  X uwu  o.o   o  X  " + "\n" +
                    "  X o ~^~. -^  o- X  " + "\n" +
                    "  X -.o o ~o. ^-  X  " + "\n" +
                    "  XXXXXXXXXXXXXXXXX  " + "\n";
                break;
        }
        ascii += "                     ";
        return ascii;
    }

    public static String arrow() {
        String ascii =
                "     "+"\n"+
                "====>"+"\n"+
                "EXIT>"+"\n"+
                "====>"+"\n"+
                "     ";
        return ascii;
    }

}
