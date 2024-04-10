package com.subho.projects.airag.config;

import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.File;
import java.util.List;

/**
 * Created by subho
 * Date: 4/10/2024
 */
@Configuration
public class simpleVectoreStoreConfig {

    @Value("${app.vectorstore.path:/tmp/vectorstore.json}")
    private String vectorStoragePath;

    @Value("${app.resource}")
    private Resource documentResource;

    @Bean
    SimpleVectorStore simpleVectorStore(EmbeddingClient embeddingClient) {
        SimpleVectorStore simpleVectorStore = new SimpleVectorStore(embeddingClient);
        File vectorStoreFile = new File(vectorStoragePath);
        if (vectorStoreFile.exists()) { // load existing vector store if exists
            simpleVectorStore.load(vectorStoreFile);
        } else { // otherwise load the documents and save the vector store
            TikaDocumentReader documentReader = new TikaDocumentReader(documentResource);
            List<Document> documents = documentReader.get();
            TextSplitter textSplitter = new TokenTextSplitter();
            List<Document> splitDocuments = textSplitter.apply(documents);
            simpleVectorStore.add(splitDocuments);
            simpleVectorStore.save(vectorStoreFile);
        }
        return simpleVectorStore;
    }
}
