package main

import demo.Example
import demo.ExampleNesting
import demo.ExamplePerson
import org.junit.Test

class SmokeTest {

    @Test
    fun smokeTest() {
        thereAndBackAgain(ExamplePerson(42, "user1"))
        thereAndBackAgain(ExamplePerson(99, null))

        var falseNesting = ExampleNesting(false)
        var trueNesting = ExampleNesting(true)
        thereAndBackAgain3(falseNesting)
        thereAndBackAgain3(trueNesting)

        thereAndBackAgain2(Example(42, falseNesting, falseNesting, "user1"))
        thereAndBackAgain2(Example(99, trueNesting, trueNesting, null))
        thereAndBackAgain2(Example(99, trueNesting, null, null))

    }

    private fun thereAndBackAgain(originalAvroSpecificRecord: ExamplePerson) {
        println("originalAvroSpecificRecord = ${originalAvroSpecificRecord}")

        val kotlinDataClass = demo.ExamplePersonKt.fromAvroSpecificRecord(originalAvroSpecificRecord)
        println("kotlinDataClass = ${kotlinDataClass}")

        val toAvroSpecificRecord = kotlinDataClass.toAvroSpecificRecord()
        println("toAvroSpecificRecord = ${toAvroSpecificRecord}")

        println()
    }

    fun thereAndBackAgain2(example: Example) {
        println("originalAvroSpecificRecord = ${example}")

        val kotlinDataClass = demo.ExampleKt.fromAvroSpecificRecord(example)
        println("kotlinDataClass = ${kotlinDataClass}")

        val toAvroSpecificRecord = kotlinDataClass.toAvroSpecificRecord()
        println("toAvroSpecificRecord = ${toAvroSpecificRecord}")

        println()
    }

    private fun thereAndBackAgain3(exampleNesting: ExampleNesting) {
        println("originalAvroSpecificRecord = ${exampleNesting}")

        val kotlinDataClass = demo.ExampleNestingKt.fromAvroSpecificRecord(exampleNesting)
        println("kotlinDataClass = ${kotlinDataClass}")

        val toAvroSpecificRecord = kotlinDataClass.toAvroSpecificRecord()
        println("toAvroSpecificRecord = ${toAvroSpecificRecord}")

        println()
    }

}