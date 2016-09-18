package com.example.samp8.calnutrition.model;

/**
 * Created by samp8 on 9/17/2016.
 */
public class Product {
        private int number;
        private String name;
        private int calorie;
        private int protein ;
        private int fat;
        private int carbs;

        public Product(int number, String name, int calorie, int protein, int fat, int carbs) {
            this.number = number;
            this.name = name;
            this.calorie = calorie;
            this.protein = protein;
            this.fat= fat;
            this.carbs=carbs;

        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number= number;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCalorie() {
            return calorie;
        }

        public void setCalorie(int calorie) {
            this.calorie= calorie;
        }

        public int getProtein() {
            return protein;
        }

        public void setProtein(int protein) {
            this.protein = protein;
        }
        public int getFat() {
            return fat;
        }

        public void setFat(int fat) {
            this.fat = fat;
        }

        public int getCarbs() {
            return carbs;
        }

        public void setCarbs(int carbs) {
            this.carbs = carbs;
        }
    }


