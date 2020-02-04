package com.aries.dxfConvert.entities;

/**
 * Created by Aries
 */
public class Color {

    /**
     * 62 color 值
     */
    private int color = -1;

    /**
     * 对应 RGB值
     */
    private int colorR = -1;
    private int colorG = -1;
    private int colorB = -1;

    public String getColorRGBStr(){
        String rgbStr = colorR + "," + colorG + "," + colorB ;
        return rgbStr;
    }


    public int getColorR() {
        return colorR;
    }

    public void setColorR(int colorR) {
        this.colorR = colorR;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        int[] rgb = getRGB(color);
        colorR = rgb[0];
        colorG = rgb[1];
        colorB = rgb[2];
    }

    public int getColorG() {
        return colorG;
    }

    public void setColorG(int colorG) {
        this.colorG = colorG;
    }

    public int getColorB() {
        return colorB;
    }

    public void setColorB(int colorB) {
        this.colorB = colorB;
    }

    private int[] getRGB(int i){
        int r,g,b;
        int[] rgb = new int[]{0,0,0};
        switch (i)
        {
            case 1: r = 255; g = 0; b = 0; break;
            case 2: r = 255; g = 255; b = 0; break;
            case 3: r = 0; g = 255; b = 0; break;
            case 4: r = 0; g = 255; b = 255; break;
            case 5: r = 0; g = 0; b = 255; break;
            case 6: r = 255; g = 0; b = 255; break;
            case 7: r = 255; g = 255; b = 255; break;
            case 8: r = 128; g = 128; b = 128; break;
            case 9: r = 192; g = 192; b = 192; break;
            case 10: r = 255; g = 0; b = 0; break;
            case 11: r = 255; g = 127; b = 127; break;
            case 12: r = 204; g = 0; b = 0; break;
            case 13: r = 204; g = 102; b = 102; break;
            case 14: r = 153; g = 0; b = 0; break;
            case 15: r = 153; g = 76; b = 76; break;
            case 16: r = 127; g = 0; b = 0; break;
            case 17: r = 127; g = 63; b = 63; break;
            case 18: r = 76; g = 0; b = 0; break;
            case 19: r = 76; g = 38; b = 38; break;
            case 20: r = 255; g = 63; b = 0; break;
            case 21: r = 255; g = 159; b = 127; break;
            case 22: r = 204; g = 51; b = 0; break;
            case 23: r = 204; g = 127; b = 102; break;
            case 24: r = 153; g = 38; b = 0; break;
            case 25: r = 153; g = 95; b = 76; break;
            case 26: r = 127; g = 31; b = 0; break;
            case 27: r = 127; g = 79; b = 63; break;
            case 28: r = 76; g = 19; b = 0; break;
            case 29: r = 76; g = 47; b = 38; break;
            case 30: r = 255; g = 127; b = 0; break;
            case 31: r = 255; g = 191; b = 127; break;
            case 32: r = 204; g = 102; b = 0; break;
            case 33: r = 204; g = 153; b = 102; break;
            case 34: r = 153; g = 76; b = 0; break;
            case 35: r = 153; g = 114; b = 76; break;
            case 36: r = 127; g = 63; b = 0; break;
            case 37: r = 127; g = 95; b = 63; break;
            case 38: r = 76; g = 38; b = 0; break;
            case 39: r = 76; g = 57; b = 38; break;
            case 40: r = 255; g = 191; b = 0; break;
            case 41: r = 255; g = 223; b = 127; break;
            case 42: r = 204; g = 153; b = 0; break;
            case 43: r = 204; g = 178; b = 102; break;
            case 44: r = 153; g = 114; b = 0; break;
            case 45: r = 153; g = 133; b = 76; break;
            case 46: r = 127; g = 95; b = 0; break;
            case 47: r = 127; g = 111; b = 63; break;
            case 48: r = 76; g = 57; b = 0; break;
            case 49: r = 76; g = 66; b = 38; break;
            case 50: r = 255; g = 255; b = 0; break;
            case 51: r = 255; g = 255; b = 127; break;
            case 52: r = 204; g = 204; b = 0; break;
            case 53: r = 204; g = 204; b = 102; break;
            case 54: r = 153; g = 153; b = 0; break;
            case 55: r = 153; g = 153; b = 76; break;
            case 56: r = 127; g = 127; b = 0; break;
            case 57: r = 127; g = 127; b = 63; break;
            case 58: r = 76; g = 76; b = 0; break;
            case 59: r = 76; g = 76; b = 38; break;
            case 60: r = 191; g = 255; b = 0; break;
            case 61: r = 223; g = 255; b = 127; break;
            case 62: r = 153; g = 204; b = 0; break;
            case 63: r = 178; g = 204; b = 102; break;
            case 64: r = 114; g = 153; b = 0; break;
            case 65: r = 133; g = 153; b = 76; break;
            case 66: r = 95; g = 127; b = 0; break;
            case 67: r = 111; g = 127; b = 63; break;
            case 68: r = 57; g = 76; b = 0; break;
            case 69: r = 66; g = 76; b = 38; break;
            case 70: r = 127; g = 255; b = 0; break;
            case 71: r = 191; g = 255; b = 127; break;
            case 72: r = 102; g = 204; b = 0; break;
            case 73: r = 153; g = 204; b = 102; break;
            case 74: r = 76; g = 153; b = 0; break;
            case 75: r = 114; g = 153; b = 76; break;
            case 76: r = 63; g = 127; b = 0; break;
            case 77: r = 95; g = 127; b = 63; break;
            case 78: r = 38; g = 76; b = 0; break;
            case 79: r = 57; g = 76; b = 38; break;
            case 80: r = 63; g = 255; b = 0; break;
            case 81: r = 159; g = 255; b = 127; break;
            case 82: r = 51; g = 204; b = 0; break;
            case 83: r = 127; g = 204; b = 102; break;
            case 84: r = 38; g = 153; b = 0; break;
            case 85: r = 95; g = 153; b = 76; break;
            case 86: r = 31; g = 127; b = 0; break;
            case 87: r = 79; g = 127; b = 63; break;
            case 88: r = 19; g = 76; b = 0; break;
            case 89: r = 47; g = 76; b = 38; break;
            case 90: r = 0; g = 255; b = 0; break;
            case 91: r = 127; g = 255; b = 127; break;
            case 92: r = 0; g = 204; b = 0; break;
            case 93: r = 102; g = 204; b = 102; break;
            case 94: r = 0; g = 153; b = 0; break;
            case 95: r = 76; g = 153; b = 76; break;
            case 96: r = 0; g = 127; b = 0; break;
            case 97: r = 63; g = 127; b = 63; break;
            case 98: r = 0; g = 76; b = 0; break;
            case 99: r = 38; g = 76; b = 38; break;
            case 100: r = 0; g = 255; b = 63; break;
            case 101: r = 127; g = 255; b = 159; break;
            case 102: r = 0; g = 204; b = 51; break;
            case 103: r = 102; g = 204; b = 127; break;
            case 104: r = 0; g = 153; b = 38; break;
            case 105: r = 76; g = 153; b = 95; break;
            case 106: r = 0; g = 127; b = 31; break;
            case 107: r = 63; g = 127; b = 79; break;
            case 108: r = 0; g = 76; b = 19; break;
            case 109: r = 38; g = 76; b = 47; break;
            case 110: r = 0; g = 255; b = 127; break;
            case 111: r = 127; g = 255; b = 191; break;
            case 112: r = 0; g = 204; b = 102; break;
            case 113: r = 102; g = 204; b = 153; break;
            case 114: r = 0; g = 153; b = 76; break;
            case 115: r = 76; g = 153; b = 114; break;
            case 116: r = 0; g = 127; b = 63; break;
            case 117: r = 63; g = 127; b = 95; break;
            case 118: r = 0; g = 76; b = 38; break;
            case 119: r = 38; g = 76; b = 57; break;
            case 120: r = 0; g = 255; b = 191; break;
            case 121: r = 127; g = 255; b = 223; break;
            case 122: r = 0; g = 204; b = 153; break;
            case 123: r = 102; g = 204; b = 178; break;
            case 124: r = 0; g = 153; b = 114; break;
            case 125: r = 76; g = 153; b = 133; break;
            case 126: r = 0; g = 127; b = 95; break;
            case 127: r = 63; g = 127; b = 111; break;
            case 128: r = 0; g = 76; b = 57; break;
            case 129: r = 38; g = 76; b = 66; break;
            case 130: r = 0; g = 255; b = 255; break;
            case 131: r = 127; g = 255; b = 255; break;
            case 132: r = 0; g = 204; b = 204; break;
            case 133: r = 102; g = 204; b = 204; break;
            case 134: r = 0; g = 153; b = 153; break;
            case 135: r = 76; g = 153; b = 153; break;
            case 136: r = 0; g = 127; b = 127; break;
            case 137: r = 63; g = 127; b = 127; break;
            case 138: r = 0; g = 76; b = 76; break;
            case 139: r = 38; g = 76; b = 76; break;
            case 140: r = 0; g = 191; b = 255; break;
            case 141: r = 127; g = 223; b = 255; break;
            case 142: r = 0; g = 153; b = 204; break;
            case 143: r = 102; g = 178; b = 204; break;
            case 144: r = 0; g = 114; b = 153; break;
            case 145: r = 76; g = 133; b = 153; break;
            case 146: r = 0; g = 95; b = 127; break;
            case 147: r = 63; g = 111; b = 127; break;
            case 148: r = 0; g = 57; b = 76; break;
            case 149: r = 38; g = 66; b = 76; break;
            case 150: r = 0; g = 127; b = 255; break;
            case 151: r = 127; g = 191; b = 255; break;
            case 152: r = 0; g = 102; b = 204; break;
            case 153: r = 102; g = 153; b = 204; break;
            case 154: r = 0; g = 76; b = 153; break;
            case 155: r = 76; g = 114; b = 153; break;
            case 156: r = 0; g = 63; b = 127; break;
            case 157: r = 63; g = 95; b = 127; break;
            case 158: r = 0; g = 38; b = 76; break;
            case 159: r = 38; g = 57; b = 76; break;
            case 160: r = 0; g = 63; b = 255; break;
            case 161: r = 127; g = 159; b = 255; break;
            case 162: r = 0; g = 51; b = 204; break;
            case 163: r = 102; g = 127; b = 204; break;
            case 164: r = 0; g = 38; b = 153; break;
            case 165: r = 76; g = 95; b = 153; break;
            case 166: r = 0; g = 31; b = 127; break;
            case 167: r = 63; g = 79; b = 127; break;
            case 168: r = 0; g = 19; b = 76; break;
            case 169: r = 38; g = 47; b = 76; break;
            case 170: r = 0; g = 0; b = 255; break;
            case 171: r = 127; g = 127; b = 255; break;
            case 172: r = 0; g = 0; b = 204; break;
            case 173: r = 102; g = 102; b = 204; break;
            case 174: r = 0; g = 0; b = 153; break;
            case 175: r = 76; g = 76; b = 153; break;
            case 176: r = 0; g = 0; b = 127; break;
            case 177: r = 63; g = 63; b = 127; break;
            case 178: r = 0; g = 0; b = 76; break;
            case 179: r = 38; g = 38; b = 76; break;
            case 180: r = 63; g = 0; b = 255; break;
            case 181: r = 159; g = 127; b = 255; break;
            case 182: r = 51; g = 0; b = 204; break;
            case 183: r = 127; g = 102; b = 204; break;
            case 184: r = 38; g = 0; b = 153; break;
            case 185: r = 95; g = 76; b = 153; break;
            case 186: r = 31; g = 0; b = 127; break;
            case 187: r = 79; g = 63; b = 127; break;
            case 188: r = 19; g = 0; b = 76; break;
            case 189: r = 47; g = 38; b = 76; break;
            case 190: r = 127; g = 0; b = 255; break;
            case 191: r = 191; g = 127; b = 255; break;
            case 192: r = 102; g = 0; b = 204; break;
            case 193: r = 153; g = 102; b = 204; break;
            case 194: r = 76; g = 0; b = 153; break;
            case 195: r = 114; g = 76; b = 153; break;
            case 196: r = 63; g = 0; b = 127; break;
            case 197: r = 95; g = 63; b = 127; break;
            case 198: r = 38; g = 0; b = 76; break;
            case 199: r = 57; g = 38; b = 76; break;
            case 200: r = 191; g = 0; b = 255; break;
            case 201: r = 223; g = 127; b = 255; break;
            case 202: r = 153; g = 0; b = 204; break;
            case 203: r = 178; g = 102; b = 204; break;
            case 204: r = 114; g = 0; b = 153; break;
            case 205: r = 133; g = 76; b = 153; break;
            case 206: r = 95; g = 0; b = 127; break;
            case 207: r = 111; g = 63; b = 127; break;
            case 208: r = 57; g = 0; b = 76; break;
            case 209: r = 66; g = 38; b = 76; break;
            case 210: r = 255; g = 0; b = 255; break;
            case 211: r = 255; g = 127; b = 255; break;
            case 212: r = 204; g = 0; b = 204; break;
            case 213: r = 204; g = 102; b = 204; break;
            case 214: r = 153; g = 0; b = 153; break;
            case 215: r = 153; g = 76; b = 153; break;
            case 216: r = 127; g = 0; b = 127; break;
            case 217: r = 127; g = 63; b = 127; break;
            case 218: r = 76; g = 0; b = 76; break;
            case 219: r = 76; g = 38; b = 76; break;
            case 220: r = 255; g = 0; b = 191; break;
            case 221: r = 255; g = 127; b = 223; break;
            case 222: r = 204; g = 0; b = 153; break;
            case 223: r = 204; g = 102; b = 178; break;
            case 224: r = 153; g = 0; b = 114; break;
            case 225: r = 153; g = 76; b = 133; break;
            case 226: r = 127; g = 0; b = 95; break;
            case 227: r = 127; g = 63; b = 111; break;
            case 228: r = 76; g = 0; b = 57; break;
            case 229: r = 76; g = 38; b = 66; break;
            case 230: r = 255; g = 0; b = 127; break;
            case 231: r = 255; g = 127; b = 191; break;
            case 232: r = 204; g = 0; b = 102; break;
            case 233: r = 204; g = 102; b = 153; break;
            case 234: r = 153; g = 0; b = 76; break;
            case 235: r = 153; g = 76; b = 114; break;
            case 236: r = 127; g = 0; b = 63; break;
            case 237: r = 127; g = 63; b = 95; break;
            case 238: r = 76; g = 0; b = 38; break;
            case 239: r = 76; g = 38; b = 57; break;
            case 240: r = 255; g = 0; b = 63; break;
            case 241: r = 255; g = 127; b = 159; break;
            case 242: r = 204; g = 0; b = 51; break;
            case 243: r = 204; g = 102; b = 127; break;
            case 244: r = 153; g = 0; b = 38; break;
            case 245: r = 153; g = 76; b = 95; break;
            case 246: r = 127; g = 0; b = 31; break;
            case 247: r = 127; g = 63; b = 79; break;
            case 248: r = 76; g = 0; b = 19; break;
            case 249: r = 76; g = 38; b = 47; break;
            case 250: r = 51; g = 51; b = 51; break;
            case 251: r = 91; g = 91; b = 91; break;
            case 252: r = 132; g = 132; b = 132; break;
            case 253: r = 173; g = 173; b = 173; break;
            case 254: r = 214; g = 214; b = 214; break;
            case 255: r = 255; g = 255; b = 255; break;
            default: r = 255; g = 255; b = 255; break;
        }
        rgb[0] = r;
        rgb[1] = g;
        rgb[2] = b;
        return rgb;
    }
}
