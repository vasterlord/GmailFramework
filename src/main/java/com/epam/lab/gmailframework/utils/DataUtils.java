package com.epam.lab.gmailframework.utils;

import com.epam.lab.gmailframework.models.*;
import com.google.common.collect.Lists;
import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class DataUtils {
    private static final Logger LOGGER = Logger.getLogger(DataUtils.class);

    private static ConfigurationProperties configurationProperties = new ConfigurationProperties();

    public static Users getUsersDataFromXML() {
        JAXBContext jaxbContext;
        Users users = new Users();
        try {
            jaxbContext = JAXBContext.newInstance(Users.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            users = (Users) jaxbUnmarshaller.unmarshal(Paths.get(configurationProperties.getUsersXMLData()).toFile());
            return users;
        } catch (JAXBException e) {
            e.printStackTrace();
            LOGGER.info(String.format("JAXBException : %s", e.getMessage()));
            return users;
        }
    }

    public static Users getUsersDataFromCSV() {
        String line;
        String cvsSplitBy = ",";
        Users users = new Users();
        List<User> userList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(configurationProperties.getUsersCSVData()))) {
            while ((line = br.readLine()) != null) {
                List<String> csvUserData = Arrays.asList(line.split(cvsSplitBy));
                User user = assignUserData(csvUserData);
                userList.add(user);
            }
            users.setUsers(userList);
            return users;
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.info(String.format("IOException : %s", e.getMessage()));
            return users;
        }
    }

    private static User assignUserData(List<String> csvUserData) {
        User user = new User();
        Letter letter = new Letter();
        user.setUserEmail(csvUserData.get(0));
        user.setUserPassword(csvUserData.get(1));
        letter.setContentLetter(csvUserData.get(2));
        letter.setReceiverEmail(csvUserData.get(3));
        letter.setSubjectText(csvUserData.get(4));
        user.setLetter(letter);
        return user;
    }

    public static Users getUsersDataFromXLSX() {
        Users users = null;
        try {
            users = new Users();
            List<User> userList = new ArrayList<>();
            List<String> xlsxUserData = new ArrayList<>();
            FileInputStream excelFile = new FileInputStream(new File(configurationProperties.getUsersXLSXData()));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet dataTypeSheet = workbook.getSheetAt(0);
            for (Row currentRow : dataTypeSheet) {
                for (int i = 0; i < currentRow.getLastCellNum(); i++) {
                    xlsxUserData.add(currentRow.getCell(i).getStringCellValue());
                }
                User user = assignUserData(xlsxUserData);
                userList.add(user);
            }
            users.setUsers(userList);
            return users;
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.info(String.format("IOException : %s", e.getMessage()));
            return users;
        }
    }


}
