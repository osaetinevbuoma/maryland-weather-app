package com.marylandweatherapp

import grails.transaction.Transactional

import jxl.CellView
import jxl.Workbook
import jxl.WorkbookSettings
import jxl.write.Label
import jxl.write.Number
import jxl.write.WritableCellFormat
import jxl.write.WritableFont
import jxl.write.WritableSheet
import jxl.write.WritableWorkbook
import jxl.write.WriteException
import jxl.write.biff.RowsExceededException

@Transactional
class StateService {

    /**
     * List all states in the database
     *
     * @return  List[State]    a list of states in the database
     */
    State[] listStates() {
        State.list(order: "asc", sort: "name")
    }

    /**
     * Save a state object in DB
     *
     * @param name                  String  name of the state to save
     * @param city                  String  name of the capital city to save
     * @param lgas                  int     number of local government areas in stat
     * @param latitude              double   latitude coordinate
     * @param longitude             double   longitude coordinate
     * @return  State   saved state object
     */
    @Transactional
    State saveState(String name, String city, int lgas, double latitude, double longitude) {
        State state = new State(name: name, city: city, lgas: lgas, latitude: latitude,
                longitude: longitude)

        if (!state.validate()) {
            return state
        }

        state.save flush: true

        return state
    }

    /**
     * Update a state object and save in DB
     *
     * @param id                    int     unique id of state
     * @param name                  String  name of the state to save
     * @param city                  String  capital city of the state to save
     * @param latitude              double   latitude coordinate
     * @param longitude             double   longitude coordinate
     * @param lgas                  int     number of local government areas in state
     * @return  State   updated state object
     */
    @Transactional
        State updateState(int id, String name, String city, int lgas, double latitude, double longitude) {
        State state = State.get(id)
        state.name = name
        state.city = city
        state.lgas = lgas
        state.latitude = latitude
        state.longitude = longitude

        if (!state.validate()) {
            return state
        }

        state.save flush: true

        return state
    }

    /**
     * Update a state object and save in DB
     *
     * @param id                    int     unique id of state
     * @param currentTempF          double   current temperature in degrees F
     * @param currentTempC          double   current temperature in degrees C
     * @param relativeHumidity      String   current relative humidity
     * @param windDirection         String  current wind direction
     * @param windSpeed             double   current wind speed in km/h
     * @param visibility            double   current visibility in km
     * @param iconUrl               String  url of the current weather icon
     * @return  State   updated state object
     */
    @Transactional
    State updateState(int id, double currentTempF,
                      double currentTempC, String relativeHumidity, String windDirection,
                      double windSpeed, double visibility, String iconUrl) {
        State state = State.get(id)
        state.currentTempF = currentTempF
        state.currentTempC = currentTempC
        state.relativeHumidity = relativeHumidity
        state.windDirection = windDirection
        state.windSpeed = windSpeed
        state.visibility = visibility
        state.iconUrl = iconUrl

        if (!state.validate()) {
            return state
        }

        state.save flush: true

        return state
    }

    /**
     * Delete a state from DB
     *
     * @param id    int unique id of state object
     */
    @Transactional
    void deleteState(int id) {
        State.get(id).delete flush: true
    }

    /**
     * Generate excel file from weather data in DB
     * @param excelType     type of excel document to generate
     * @return File an excel file
     */
    File generateExcelFile(String excelType) {
        WritableCellFormat tahomaBold   // tahoma bold font
        WritableCellFormat tahoma       // tahoma font

        WritableFont tahoma12pt = new WritableFont(WritableFont.TAHOMA, 12) // font size 12
        tahoma = new WritableCellFormat(tahoma12pt)
        tahoma.setWrap(true)    // wrap lines

        WritableFont tahoma12ptBold = new WritableFont(WritableFont.TAHOMA, 12, WritableFont.BOLD, false) // bold font size 14
        tahomaBold = new WritableCellFormat(tahoma12ptBold)
        tahomaBold.setWrap(true) // wrap lines

        // Prepare the cells with the font type and sizes
        CellView cv = new CellView()
        cv.setFormat(tahoma)
        cv.setFormat(tahomaBold)
        cv.setAutosize(true)

        File file = new File("weather_app.${excelType}")
        WorkbookSettings workbookSettings = new WorkbookSettings()

        workbookSettings.setLocale(new Locale("en", "EN"))

        WritableWorkbook workbook = Workbook.createWorkbook(file, workbookSettings)
        workbook.createSheet("Weather Report", 0)   // Worksheet name
        WritableSheet excelSheet = workbook.getSheet(0)
        
        createColumnTitle(excelSheet, tahomaBold)
        createColumnContent(excelSheet, tahoma)

        workbook.write()
        workbook.close()

        return file
    }

    /**
     * First row of work sheet should be the titles of each column
     *
     * @param sheet             the work sheet to create
     * @param tahomaBold        tahoma bold font
     * @param tahoma            tahoma font
     * @throws WriteException
     */
    private static void createColumnTitle(WritableSheet sheet, WritableCellFormat tahomaBold) 
            throws WriteException {
        // Column titles
        addColumnTitle(sheet, 0, 0, "State", tahomaBold)
        addColumnTitle(sheet, 1, 0, "Capital", tahomaBold)
        addColumnTitle(sheet, 2, 0, "Local Governments Areas", tahomaBold)
        addColumnTitle(sheet, 3, 0, "Latitude", tahomaBold)
        addColumnTitle(sheet, 4, 0, "Longitude", tahomaBold)
        addColumnTitle(sheet, 5, 0, "Current Temperature (F)", tahomaBold)
        addColumnTitle(sheet, 6, 0, "Current Temperature (C)", tahomaBold)
        addColumnTitle(sheet, 7, 0, "Relative Humidity", tahomaBold)
        addColumnTitle(sheet, 8, 0, "Wind Direction", tahomaBold)
        addColumnTitle(sheet, 9, 0, "Wind Speed", tahomaBold)
        addColumnTitle(sheet, 10, 0, "Wind Visibility", tahomaBold)
    }

    /**
     * Populate worksheet rows with weather data
     *
     * @param sheet                     the work sheet being created
     * @param tahoma                    tahoma font
     * @throws WriteException
     * @throws RowsExceededException
     */
    private static void createColumnContent(WritableSheet sheet, WritableCellFormat tahoma) 
            throws WriteException, RowsExceededException {
        int row = 1
        List<State> states = State.list(sort: "name", order: "asc")
        states.each { state ->
            addString(sheet, 0, row, state.name, tahoma)
            addString(sheet, 1, row, state.city, tahoma)
            addNumber(sheet, 2, row, state.lgas, tahoma)
            addNumber(sheet, 3, row, state.latitude, tahoma)
            addNumber(sheet, 4, row, state.longitude, tahoma)
            addNumber(sheet, 5, row, state.currentTempF, tahoma)
            addNumber(sheet, 6, row, state.currentTempC, tahoma)
            addString(sheet, 7, row, state.relativeHumidity, tahoma)
            addString(sheet, 8, row, state.windDirection, tahoma)
            addNumber(sheet, 9, row, state.windSpeed, tahoma)
            addNumber(sheet, 10, row, state.visibility, tahoma)

            row++
        }

        addString(sheet, 0, row, "", tahoma)

        Date now = new Date()
        addString(sheet, 0, row+1, "Downloaded: ${now.getDateString()} at ${now.getTimeString()}", tahoma)
    }

    /**
     * Write column titles into cells
     *
     * @param sheet                 worksheet being created
     * @param column                column index
     * @param row                   row index
     * @param s                     string to write to cell
     * @param tahomaBold            tahoma bold font type
     * @throws RowsExceededException
     * @throws WriteException
     */
    private static void addColumnTitle(WritableSheet sheet, int column, int row, String s, 
                                   WritableCellFormat tahomaBold) throws RowsExceededException, 
            WriteException {
        jxl.write.Label label
        label = new jxl.write.Label(column, row, s, tahomaBold)
        sheet.addCell(label)
    }

    /**
     * Write numbers to cells
     *
     * @param sheet                     worksheet being created
     * @param column                    column index
     * @param row                       row index
     * @param aDouble                   number to write to cell
     * @param tahoma                    tahoma font type
     * @throws WriteException
     * @throws RowsExceededException
     */
    private static void addNumber(WritableSheet sheet, int column, int row, Double aDouble, 
                                  WritableCellFormat tahoma) throws WriteException, RowsExceededException {
        jxl.write.Number number
        number = new jxl.write.Number(column, row, aDouble, tahoma)
        sheet.addCell(number)
    }

    /**
     * Write string to cells
     *
     * @param sheet                     worksheet being created
     * @param column                    column index
     * @param row                       row index
     * @param s                         string to write to cell
     * @param tahoma                    tahoma font type
     * @throws WriteException
     * @throws RowsExceededException
     */
    private static void addString(WritableSheet sheet, int column, int row, String s, 
                                 WritableCellFormat tahoma) throws WriteException, RowsExceededException {
        jxl.write.Label label
        label = new jxl.write.Label(column, row, s, tahoma)
        sheet.addCell(label)
    }
}
