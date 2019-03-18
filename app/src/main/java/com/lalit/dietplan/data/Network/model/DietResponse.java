package com.lalit.dietplan.data.Network.model;

import java.util.List;

public class DietResponse {


    /**
     * diet_duration : 20
     * week_diet_data : {"thursday":[{"food":"scramblled eggs","meal_time":"08:00"},{"food":"Burrito bowls","meal_time":"14:00"},{"food":"Evening snacks","meal_time":"18:00"},{"food":"North Indian thali","meal_time":"22:00"}],"wednesday":[{"food":"Sprouts","meal_time":"07:00"},{"food":"Bread lintils and Rice","meal_time":"16:00"},{"food":"Soup ,Rice and Chicken","meal_time":"21:00"}],"monday":[{"food":"Warm honey and water","meal_time":"07:00"},{"food":"proper thali","meal_time":"15:00"}]}
     */

    private int diet_duration;
    private WeekDietDataBean week_diet_data;

    public int getDiet_duration() {
        return diet_duration;
    }

    public void setDiet_duration(int diet_duration) {
        this.diet_duration = diet_duration;
    }

    public WeekDietDataBean getWeek_diet_data() {
        return week_diet_data;
    }

    public void setWeek_diet_data(WeekDietDataBean week_diet_data) {
        this.week_diet_data = week_diet_data;
    }

    public static class WeekDietDataBean {
        private List<ThursdayBean> thursday;
        private List<WednesdayBean> wednesday;
        private List<MondayBean> monday;

        public List<ThursdayBean> getThursday() {
            return thursday;
        }

        public void setThursday(List<ThursdayBean> thursday) {
            this.thursday = thursday;
        }

        public List<WednesdayBean> getWednesday() {
            return wednesday;
        }

        public void setWednesday(List<WednesdayBean> wednesday) {
            this.wednesday = wednesday;
        }

        public List<MondayBean> getMonday() {
            return monday;
        }

        public void setMonday(List<MondayBean> monday) {
            this.monday = monday;
        }

        public static class ThursdayBean {
            /**
             * food : scramblled eggs
             * meal_time : 08:00
             */

            private String food;
            private String meal_time;

            public String getFood() {
                return food;
            }

            public void setFood(String food) {
                this.food = food;
            }

            public String getMeal_time() {
                return meal_time;
            }

            public void setMeal_time(String meal_time) {
                this.meal_time = meal_time;
            }
        }

        public static class WednesdayBean {
            /**
             * food : Sprouts
             * meal_time : 07:00
             */

            private String food;
            private String meal_time;

            public String getFood() {
                return food;
            }

            public void setFood(String food) {
                this.food = food;
            }

            public String getMeal_time() {
                return meal_time;
            }

            public void setMeal_time(String meal_time) {
                this.meal_time = meal_time;
            }
        }

        public static class MondayBean {
            /**
             * food : Warm honey and water
             * meal_time : 07:00
             */

            private String food;
            private String meal_time;

            public String getFood() {
                return food;
            }

            public void setFood(String food) {
                this.food = food;
            }

            public String getMeal_time() {
                return meal_time;
            }

            public void setMeal_time(String meal_time) {
                this.meal_time = meal_time;
            }
        }
    }
}
