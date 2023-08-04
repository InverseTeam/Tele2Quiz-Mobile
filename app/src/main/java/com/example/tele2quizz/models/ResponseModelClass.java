package com.example.tele2quizz.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseModelClass {

    @SerializedName("auth_token")
    private String authToken;
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("firstname")
    private String firstname;
    @SerializedName("lastname")
    private String lastname;
    @SerializedName("age")
    private int age;
    @SerializedName("points")
    private int points;
    @SerializedName("phone_number")
    private String phone_number;
    @SerializedName("title")
    private String title;
    @SerializedName("content")
    private String content;
    @SerializedName("category")
    private ResponseModelClass category;
    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private String id;
    @SerializedName("description")
    private String description;
    @SerializedName("question_points")
    private int questionPoints;
    @SerializedName("questions")
    private ArrayList<ResponseModelClass> questions;
    @SerializedName("question")
    private String question;
    @SerializedName("answers")
    private ArrayList<ResponseModelClass> answers;
    @SerializedName("right_answer")
    private ResponseModelClass rightAnswer;

    public ArrayList<ResponseModelClass> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<ResponseModelClass> questions) {
        this.questions = questions;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<ResponseModelClass> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<ResponseModelClass> answers) {
        this.answers = answers;
    }

    public ResponseModelClass getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(ResponseModelClass rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuestionPoints() {
        return questionPoints;
    }

    public void setQuestionPoints(int questionPoints) {
        this.questionPoints = questionPoints;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ResponseModelClass getCategory() {
        return category;
    }

    public void setCategory(ResponseModelClass category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
