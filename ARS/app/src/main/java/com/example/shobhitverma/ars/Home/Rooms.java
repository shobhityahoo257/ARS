 package com.example.shobhitverma.ars.Home;


//package com.example.shobhitverma.salesteammanagement.ChatSystem;

 public class Rooms {
     String image;
     String name;
     String address;
     String rating;
     String rent;



     public Rooms(){

     }

     public Rooms(String image, String name, String address, String rating, String rent) {
         this.image = image;
         this.name = name;
         this.address = address;
         this.rating = rating;
         this.rent = rent;
     }

     public String getImage() {
         return image;
     }

     public String getName() {
         return name;
     }

     public String getAddress() {
         return address;
     }

     public String getRating() {
         return rating;
     }

     public String getRent() {
         return rent;
     }

     public void setImage(String image) {
         this.image = image;
     }

     public void setName(String name) {
         this.name = name;
     }

     public void setAddress(String address) {
         this.address = address;
     }

     public void setRating(String rating) {
         this.rating = rating;
     }

     public void setRent(String rent) {
         this.rent = rent;
     }
 }
