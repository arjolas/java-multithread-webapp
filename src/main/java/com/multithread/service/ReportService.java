package com.multithread.service;

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
    // Esempio: leggi dal DB i record con id minore di blockId * 10
    List<EntityModel> records = recordRepository.findAll().stream()
      .filter(r -> r.getId() < blockId * 10)
      .toList();

    int somma = records.stream().mapToInt(EntityModel::getValore).sum();

    return String.format("Blocco %d - Somma valori: %d (da DB)", blockId, somma);
  }

  @PreDestroy
  public void cleanup() {
    executorService.shutdown();
  }
}
