package net.avro2kotlin.compiler

interface KotlinAvroConverter<K, A> {
    fun toAvroSpecificRecord(k: K): A
    fun fromAvroSpecificRecord(a: A): K
}

