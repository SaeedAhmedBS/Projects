package lcdtest;

import com.pi4j.component.lcd.impl.GpioLcdDisplay;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.RaspiPin;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class mainLCD {

    String[] screens = {
        "     Welcome To         The Dark Sat       By: ScorpiSoft   v01.0         A-Next",
        "       login        username:                               B-Back        A-Next",
        "       login        Password:                               B-Back        A-Next",
        "       login            Welcome User                        B-Back        A-Next",
        "   Welcome User      Press the choice    number to select   B-Back        A-Next",
        "1-show device IP    2-connection state  3-Memory size       B-Back        A-Next",
        "Device IP           192.168...                              B-Back              ",
        "  connection state      connected                           B-Back              ",
        "Memory size         Total size: 16gb    free size: 15.2gb   B-Back              ",
        "4-Encrypt usb       Dcrypt usb          Start Server        B-Back        A-Next",
        "     Encrypt USB        USB Encrypted           done!       B-Back              ",
        "     Encrypt USB        USB Encrypted           done!       B-Back              ",
        "Server : ON         other RP IP to send:192.168...          B-Back        A-Next",
        "Server : ON         1-send file         2-voice meassage    B-Back        A-Next",
        "Server : ON         connect the MIC     Hold 'c' to record  B-Back        A-Next",
        "Server : ON         choose file                             B-Back        A-Next",
        "Server : ON         sending...          done!               B-Back        A-Next"
    };

    int LCD_ROWS = 4;
    int LCD_ROW_1 = 0;
    int LCD_ROW_2 = 1;
    int LCD_COLUMNS = 20;
    int LCD_BITS = 4;

    GpioLcdDisplay lcd = new GpioLcdDisplay(LCD_ROWS, // number of row supported by LCD
            LCD_COLUMNS, // number of columns supported by LCD
            RaspiPin.GPIO_21, // LCD RS pin
            RaspiPin.GPIO_22, // LCD strobe pin
            RaspiPin.GPIO_23, // LCD data bit 1
            RaspiPin.GPIO_24, // LCD data bit 2
            RaspiPin.GPIO_25, // LCD data bit 3
            RaspiPin.GPIO_26); // LCD data bit 4

    private void showWelcomeScreen() {
        lcd.write(0, "     Welcome To");
        lcd.write(1, "    The Dark Sat");
        lcd.write(2, "   By: ScorpiSoft");
        lcd.write(3, "v01.0         A-Next");
    }

    public static void main(String args[]) throws InterruptedException {

        GpioController gpio = GpioFactory.getInstance();
        mainLCD m = new mainLCD();

        // clear LCD
        m.lcd.clear();
        Thread.sleep(1000);
        m.showAll();
        
        // write line 1 to LCD
        m.lcd.write(m.screens[0]);
        //m.showWelcomeScreen();
        Thread.sleep(5000);
        m.lcd.clear();
        gpio.shutdown(); //  <--- implement this method call if you wish to terminate the Pi4J GPIO controller
    }

    private void showAll() {
        for (String screen : screens) {
            try {
                lcd.write(screen);
                Thread.sleep(5000);
            }catch (InterruptedException ex) {
                Logger.getLogger(mainLCD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
