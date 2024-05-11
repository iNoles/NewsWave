package com.jonathansteele.news

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.serialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class PersistentListSerializer(private val dataSerializer: KSerializer<News.Article>) : KSerializer<ImmutableList<News.Article>> {
    private class ImmutableListDescriptor : SerialDescriptor by serialDescriptor<List<News.Article>>() {
        @ExperimentalSerializationApi
        override val serialName: String = "kotlinx.collections.immutable.ImmutableList"
    }

    override val descriptor: SerialDescriptor = ImmutableListDescriptor()

    override fun serialize(
        encoder: Encoder,
        value: ImmutableList<News.Article>,
    ) {
        return ListSerializer(dataSerializer).serialize(encoder, value.toList())
    }

    override fun deserialize(decoder: Decoder): ImmutableList<News.Article> {
        return ListSerializer(dataSerializer).deserialize(decoder).toImmutableList()
    }
}
