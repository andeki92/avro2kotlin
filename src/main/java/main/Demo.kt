package main

import demo.*

fun main(args: Array<String>) {
    build()
    run()
}

private fun build() {
    val schemas = mutableListOf(SchemaUtils.getSchemaForAvsc("example.avsc"))
    schemas.addAll(SchemaUtils.getSchemasForAvdl("example.avdl"))

    schemas
            .map { schema -> KotlinGenerator.generate(schema) }
            .forEach { fileSpec -> fileSpec.writeTo(System.out) }
}

private fun run() {
    thereAndBackAgain(ExamplePerson(42, "user1"))
    thereAndBackAgain(ExamplePerson(99, null))
//    thereAndBackAgain(ExamplePerson(null, "user2"))

    var falseNesting = ExampleNesting(false)
    var trueNesting = ExampleNesting(true)
    thereAndBackAgain3(falseNesting)
    thereAndBackAgain3(trueNesting)
//    thereAndBackAgain3(ExampleNesting(null))

    thereAndBackAgain2(Example(42, falseNesting, "user1"))
    thereAndBackAgain2(Example(99, trueNesting, null))

}

private fun thereAndBackAgain(originalAvroSpecificRecord: ExamplePerson) {
    println("originalAvroSpecificRecord = ${originalAvroSpecificRecord}")

    val kotlinDataClass = ExamplePersonKt.fromAvroSpecificRecord(originalAvroSpecificRecord)
    println("kotlinDataClass = ${kotlinDataClass}")

    val toAvroSpecificRecord = kotlinDataClass.toAvroSpecificRecord()
    println("toAvroSpecificRecord = ${toAvroSpecificRecord}")

    println()
}

private fun thereAndBackAgain2(example: Example) {
    println("originalAvroSpecificRecord = ${example}")

    val kotlinDataClass = ExampleKt.fromAvroSpecificRecord(example)
    println("kotlinDataClass = ${kotlinDataClass}")

    val toAvroSpecificRecord = kotlinDataClass.toAvroSpecificRecord()
    println("toAvroSpecificRecord = ${toAvroSpecificRecord}")

    println()
}

private fun thereAndBackAgain3(exampleNesting: ExampleNesting) {
    println("originalAvroSpecificRecord = ${exampleNesting}")

    val kotlinDataClass = ExampleNestingKt.fromAvroSpecificRecord(exampleNesting)
    println("kotlinDataClass = ${kotlinDataClass}")

    val toAvroSpecificRecord = kotlinDataClass.toAvroSpecificRecord()
    println("toAvroSpecificRecord = ${toAvroSpecificRecord}")

    println()
}