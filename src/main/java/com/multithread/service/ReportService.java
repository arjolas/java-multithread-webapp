package com.multithread.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;

import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;
import com.multithread.repository.*;
import com.multithread.model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
public class ReportService {

  private final ExecutorService executorService = Executors.newFixedThreadPool(4);
  private final EntityRepository recordRepository;

  public ReportService(EntityRepository recordRepository) {
    this.recordRepository = recordRepository;
  }

  public String generateReport() {
    List<Future<String>> futures = new ArrayList<>();

    for (int i = 1; i <= 4; i++) {
      int blockId = i;
      Future<String> future = executorService.submit(() -> processBlock(blockId));
      futures.add(future);
    }

    StringBuilder report = new StringBuilder("Report:\n");
    for (Future<String> future : futures) {
      try {
        report.append(future.get()).append("\n");
      } catch (InterruptedException | ExecutionException e) {
        report.append("Errore: ").append(e.getMessage()).append("\n");
      }
    }

    return report.toString();
  }


  private String processBlock(int blockId) {
    int blockSize = 10;
    int pageNumber = blockId - 1; // Le pagine partono da 0

    Page<EntityModel> page = recordRepository.findAll(PageRequest.of(pageNumber, blockSize));

    List<EntityModel> records = page.getContent();

    int somma = records.stream().mapToInt(EntityModel::getValore).sum();
    double media = records.isEmpty() ? 0.0 : (double) somma / records.size();

    return String.format("Blocco %d - Somma: %d, Media: %.2f", blockId, somma, media);
  }

  @PreDestroy
  public void cleanup() {
    executorService.shutdown();
  }
}
