package com.sparta.shahid.employees;

import java.text.SimpleDateFormat;

public class Validation {

    public boolean isValid (String[] validCheck){
        if(isNumberValid(validCheck[0])){
            if(isTitleValid(validCheck[1])) {
                if(isName(validCheck[2])){
                    if(isMiddleInitialValid(validCheck[3])){
                        if(isName(validCheck[4])){
                            if(isGenderValid(validCheck[5])){
                                if(isEmailValid(validCheck[6])){
                                    if(isDateValid(validCheck[7])){
                                        if(isDateValid(validCheck[8])){
                                            if(isNumberValid(validCheck[9])){
                                                return true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


        return false;
    }

    private boolean isTitleValid (String title) {
       String[] array = new String[] {"Prof.", "Mrs.", "Mr.", "Ms.", "Dr.", "Drs.", "Hon."};
       for (int i = 0;i<array.length;i++){
           if(title.equals(array[i])){
               return true;
           }
       }
       return false;
    }


    private boolean isGenderValid (String gender) {
        if (gender.length() == 1 && (gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F"))){
            return true;
        }
        return false;
    }

    private boolean isMiddleInitialValid (String middleInitial){
        if(middleInitial.length() == 1) {
            for(char c : middleInitial.toCharArray()){
                if(Character.isLetter(c)){
                    return true;
                }
            }
        }

        return false;
    }


    private static boolean isEmailValid(String email){
        return email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$");
    }


    private boolean isName (String name){
        for(char n : name.toCharArray()){
            if(!Character.isLetter(n)){
                return false;
            }
        }
        return true;
    }

    private boolean isNumberValid(String number){
        try {
            Integer.parseInt(number);
        }
        catch(Exception e){
            return false;
        }

        return true;
    }

    private boolean isDateValid(String date){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M/d/yyyy");

        try {
            simpleDateFormat.parse(date);
        }
        catch(Exception e){
            return false;
        }

        return true;
    }



}
