package com.example.quiz2

var list = listOf(
    QuestionList(
        "Which planet is known as the Green Planet?",
        listOf("Venus", "Jupiter", "Mars", "Mercury"),
        "Mars"
    ), QuestionList(
        "Which planet is known as the Red Planet?",
        listOf("Venus", "Jupiter", "Mars", "Mercury"),
        "Mars"
    ), QuestionList(
        "Which planet is known as the Red Planet?",
        listOf("Venus", "Jupiter", "Mars", "Mercury"),
        "Mars"
    ), QuestionList("Capital of France?", listOf("Madrid", "Berlin", "Paris", "Rome"), "Paris")
)

class QuestionList(val question: String, val countries: List<String>, val country: String) {

}


