/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metrogram;

import util.SVGImageCanvas;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.location.LocationException;
import javax.microedition.location.LocationProvider;
import javax.microedition.location.QualifiedCoordinates;
import javax.microedition.m2g.SVGImage;
import util.MetrogramStream;

/**
 * @author aslak
 */
public class MobilYr extends MIDlet implements CommandListener {

    private boolean midletPaused = false;

    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private Command exitCommand;
    private Command search;
    private Command gpsLocation;
    private Form form;
    private StringItem stringItem;
    private TextField xposisjon;
    private TextField yposisjon;
    //</editor-fold>//GEN-END:|fields|0|
    private Command BACK = new Command("Tilbake", Command.BACK, 0);
    private SVGImageCanvas canvas = null;

    /**
     * The MobilYr constructor.
     */
    public MobilYr() {
    }


    //<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">//GEN-BEGIN:|3-startMIDlet|0|3-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Started point.
     */
    public void startMIDlet() {//GEN-END:|3-startMIDlet|0|3-preAction
        // write pre-action user code here
        switchDisplayable(null, getForm());//GEN-LINE:|3-startMIDlet|1|3-postAction
    // write post-action user code here
    }//GEN-BEGIN:|3-startMIDlet|2|
    //</editor-fold>//GEN-END:|3-startMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: switchDisplayable ">//GEN-BEGIN:|5-switchDisplayable|0|5-preSwitch
    /**
     * Switches a current displayable in a display. The <code>display</code> instance is taken from <code>getDisplay</code> method. This method is used by all actions in the design for switching displayable.
     * @param alert the Alert which is temporarily set to the display; if <code>null</code>, then <code>nextDisplayable</code> is set immediately
     * @param nextDisplayable the Displayable to be set
     */
    public void switchDisplayable(Alert alert, Displayable nextDisplayable) {//GEN-END:|5-switchDisplayable|0|5-preSwitch
        // write pre-switch user code here
        Display display = getDisplay();//GEN-BEGIN:|5-switchDisplayable|1|5-postSwitch
        if (alert == null) {
            display.setCurrent(nextDisplayable);
        } else {
            display.setCurrent(alert, nextDisplayable);
        }//GEN-END:|5-switchDisplayable|1|5-postSwitch
    // write post-switch user code here
    }//GEN-BEGIN:|5-switchDisplayable|2|
    //</editor-fold>//GEN-END:|5-switchDisplayable|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">//GEN-BEGIN:|7-commandAction|0|7-preCommandAction
    /**
     * Called by a system to indicated that a command has been invoked on a particular displayable.
     * @param command the Command that was invoked
     * @param displayable the Displayable where the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {//GEN-END:|7-commandAction|0|7-preCommandAction
        System.out.println(command.getLabel() + command.getCommandType());

        if (displayable == form) {//GEN-BEGIN:|7-commandAction|1|19-preAction
            if (command == exitCommand) {//GEN-END:|7-commandAction|1|19-preAction
                // write pre-action user code here
                System.out.println("exit");
                exitMIDlet();//GEN-LINE:|7-commandAction|2|19-postAction
            // write post-action user code here
            } else if (command == search) {//GEN-LINE:|7-commandAction|3|24-preAction
                System.out.println("search");
                // write pre-action user code here
//GEN-LINE:|7-commandAction|4|24-postAction
                // write post-action user code here
                switchDisplayable(null, getSvgViewer());
            } else if (command == gpsLocation) {
                Thread t = new Thread() {

                    public void run() {
                        System.out.println("starting looking for cordinates");
                        QualifiedCoordinates cords = null;
                        try {
                            System.out.print("waiting for cordinates ...");
                            int TIMEOUT = 30;
                            cords = LocationProvider.getInstance(null).getLocation(TIMEOUT).getQualifiedCoordinates();
                            System.out.println("ok");
                        }catch(LocationException e){
                            new Alert("Gps problem", "Kunne ikke skaffe gps posisjon før tiden gikk ut", null, null);
                        }catch (Exception e) {
                            new Alert("Problem", "Problemer med å skaffe gps posisjon, " + e.getMessage(), null,null);
                        }

                        System.out.println("Found cordinates");

                        yposisjon.setString(Double.toString(cords.getLatitude()));
                        xposisjon.setString(Double.toString(cords.getLongitude()));
                    }
                };

                t.start();
            }
        //GEN-BEGIN:|7-commandAction|5|7-postCommandAction
        }//GEN-END:|7-commandAction|5|7-postCommandAction
        // write post-action user code here


        if (command == BACK) {

            getDisplay().setCurrent(getForm());
        }
    }//GEN-BEGIN:|7-commandAction|6|
    //</editor-fold>//GEN-END:|7-commandAction|6|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand ">//GEN-BEGIN:|18-getter|0|18-preInit
    /**
     * Returns an initiliazed instance of exitCommand component.
     * @return the initialized component instance
     */
    public Command getExitCommand() {
        if (exitCommand == null) {//GEN-END:|18-getter|0|18-preInit
            // write pre-init user code here
            exitCommand = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|18-getter|1|18-postInit
        // write post-init user code here
        }//GEN-BEGIN:|18-getter|2|
        return exitCommand;
    }
    //</editor-fold>//GEN-END:|18-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: form ">//GEN-BEGIN:|14-getter|0|14-preInit
    /**
     * Returns an initiliazed instance of form component.
     * @return the initialized component instance
     */
    public Form getForm() {
        if (form == null) {//GEN-END:|14-getter|0|14-preInit
            // write pre-init user code here
            form = new Form("Welcome", new Item[]{getStringItem(), getXposisjon(), getYposisjon()});//GEN-BEGIN:|14-getter|1|14-postInit
            form.addCommand(getExitCommand());
            form.addCommand(getSearch());
            form.addCommand(getGpsLocationButton());
            form.setCommandListener(this);//GEN-END:|14-getter|1|14-postInit
        // write post-init user code here
        }//GEN-BEGIN:|14-getter|2|
        return form;
    }
    //</editor-fold>//GEN-END:|14-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem ">//GEN-BEGIN:|16-getter|0|16-preInit
    /**
     * Returns an initiliazed instance of stringItem component.
     * @return the initialized component instance
     */
    public StringItem getStringItem() {
        if (stringItem == null) {//GEN-END:|16-getter|0|16-preInit
            // write pre-init user code here
            stringItem = new StringItem("", "Mobil yr - Metrogram");//GEN-LINE:|16-getter|1|16-postInit
        // write post-init user code here
        }//GEN-BEGIN:|16-getter|2|
        return stringItem;
    }
    //</editor-fold>//GEN-END:|16-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: search ">//GEN-BEGIN:|23-getter|0|23-preInit
    /**
     * Returns an initiliazed instance of search component.
     * @return the initialized component instance
     */
    public Command getSearch() {
        if (search == null) {//GEN-END:|23-getter|0|23-preInit

            // write pre-init user code here
            search = new Command("Ok", Command.OK, 0);//GEN-LINE:|23-getter|1|23-postInit
        // write post-init user code here

        }//GEN-BEGIN:|23-getter|2|
        return search;
    }
    //</editor-fold>//GEN-END:|23-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: xposisjon ">//GEN-BEGIN:|21-getter|0|21-preInit
    /**
     * Returns an initiliazed instance of xposisjon component.
     * @return the initialized component instance
     */
    public TextField getXposisjon() {
        if (xposisjon == null) {//GEN-END:|21-getter|0|21-preInit
            // write pre-init user code here
            xposisjon = new TextField("Longitude", null, 32, TextField.ANY);//GEN-LINE:|21-getter|1|21-postInit
        // write post-init user code here
        }//GEN-BEGIN:|21-getter|2|
        return xposisjon;
    }
    //</editor-fold>//GEN-END:|21-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: yposisjon ">//GEN-BEGIN:|22-getter|0|22-preInit
    /**
     * Returns an initiliazed instance of yposisjon component.
     * @return the initialized component instance
     */
    public TextField getYposisjon() {
        if (yposisjon == null) {//GEN-END:|22-getter|0|22-preInit
            // write pre-init user code here
            yposisjon = new TextField("Latitude", null, 32, TextField.ANY);//GEN-LINE:|22-getter|1|22-postInit
        // write post-init user code here
        }//GEN-BEGIN:|22-getter|2|
        return yposisjon;
    }
    //</editor-fold>//GEN-END:|22-getter|2|

    /**
     * Returns a display instance.
     * @return the display instance.
     */
    public Display getDisplay() {
        return Display.getDisplay(this);
    }

    public Form getSvgViewer() {
        final double lon = Double.valueOf(getXposisjon().getString()).doubleValue();
        final double lat = Double.valueOf(getYposisjon().getString()).doubleValue();

        // display waiting screen
        Form wait = new Form("Waiting");

        wait.append(new StringItem("", "Downloading metrogram"));

        Thread t = new Thread() {

            public void run() {
                fetchMetrogram(lat, lon, -1);
            }
        };

        t.start();

        return wait;
    }

    private Command getGpsLocationButton() {
        if (LocationProvider.AVAILABLE == 1) {
            gpsLocation = new Command("Gps location", Command.OK, 0);
            return gpsLocation;
        } else {
            return null;
        }

    }

    public void fetchMetrogram(double lat, double lon, int height) {
        SVGImageCanvas canvas = null;


        try {
            MetrogramStream stream = new MetrogramStream();
            if (lat > 0) {
                stream.setLat(lat);
            }
            if (lon > 0) {
                stream.setLon(lon);
            }
            if (height > 0) {
                stream.setHeight(height);
            }
            SVGImage svgImage = (SVGImage) SVGImage.createImage(stream.fetch(), null);
            canvas = new SVGImageCanvas(svgImage);


            canvas.addCommand(BACK);

            canvas.setCommandListener(this);
        } catch (Exception e) {

            System.err.println("#Error:" + e.getMessage());
            e.printStackTrace();
        }

        //switching to the canvas
        getDisplay().setCurrent(canvas);
    }

    /**
     * Exits MIDlet.
     */
    public void exitMIDlet() {
        switchDisplayable(null, null);
        destroyApp(true);
        notifyDestroyed();
    }

    /**
     * Called when MIDlet is started.
     * Checks whether the MIDlet have been already started and initialize/starts or resumes the MIDlet.
     */
    public void startApp() {
        if (!midletPaused) {
            startMIDlet();
        }
        midletPaused = false;
    }

    /**
     * Called when MIDlet is paused.
     */
    public void pauseApp() {
        midletPaused = true;
    }

    /**
     * Called to signal the MIDlet to terminate.
     * @param unconditional if true, then the MIDlet has to be unconditionally terminated and all resources has to be released.
     */
    public void destroyApp(boolean unconditional) {
    }
}
