package com.pao.laboratory09.exercise2;

//import com.pao.laboratory09.exercise1.TipTranzactie;

import com.pao.laboratory09.exercise1.TipTranzactie;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.*;

public class Main {
    private static final String OUTPUT_FILE = "output/lab09_ex2.bin";
    private static final int RECORD_SIZE = 32;

    public static void main(String[] args) throws Exception {
        // TODO: Implementează conform Readme.md
        //
        // 1. Citește N din stdin, apoi cele N tranzacții (id suma data tip)
        // 2. Scrie toate înregistrările în OUTPUT_FILE cu DataOutputStream (format binar, RECORD_SIZE=32 bytes/înreg.)
        //    - bytes 0-3:   id (int, little-endian via ByteBuffer)
        //    - bytes 4-11:  suma (double, little-endian via ByteBuffer)
        //    - bytes 12-21: data (String, 10 chars ASCII, paddat cu spații la dreapta)
        //    - byte 22:     tip (0=CREDIT, 1=DEBIT)
        //    - byte 23:     status (0=PENDING, 1=PROCESSED, 2=REJECTED)
        //    - bytes 24-31: padding (zerouri)
        // 3. Procesează comenzile din stdin până la EOF cu RandomAccessFile:
        //    - READ idx       → seek(idx * RECORD_SIZE), citește și afișează înregistrarea
        //    - UPDATE idx ST  → seek(idx * RECORD_SIZE + 23), scrie noul status (0/1/2)
        //                       afișează "Updated [idx]: STATUS"
        //    - PRINT_ALL      → citește și afișează toate înregistrările
        //
        // Format linie output:
        //   [idx] id=<id> data=<data> tip=<CREDIT|DEBIT> suma=<suma:.2f> RON status=<STATUS>
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);

        if (!sc.hasNextInt()) return;
        int n = sc.nextInt();

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(OUTPUT_FILE))) {
            for (int i = 0; i < n; i++) {
                int id = sc.nextInt();
                double suma = sc.nextDouble();
                String data = sc.next();
                TipTranzactie tip = TipTranzactie.valueOf(sc.next());

                byte[] record = new byte[RECORD_SIZE];
                ByteBuffer bb = ByteBuffer.wrap(record).order(ByteOrder.LITTLE_ENDIAN);

                bb.putInt(id);
                bb.putDouble(suma);

                byte[] dataBytes = data.getBytes();
                for (int j = 0; j < 10; j++) {
                    record[12 + j] = (j < dataBytes.length) ? dataBytes[j] : (byte) ' ';
                }

                record[22] = (byte) (tip == TipTranzactie.CREDIT ? 0 : 1);

                record[23] = (byte) 0;

                dos.write(record);
            }
        }

        try (RandomAccessFile raf = new RandomAccessFile(OUTPUT_FILE, "rw")) {
            while (sc.hasNext()) {
                String cmd = sc.next();
                switch (cmd) {
                    case "READ":
                        if (sc.hasNextInt()) {
                            int idx = sc.nextInt();
                            printRecord(raf, idx);
                        }
                        break;

                    case "UPDATE":
                        if (sc.hasNextInt()) {
                            int idx = sc.nextInt();
                            String statusStr = sc.next();
                            byte statusByte = encodeStatus(statusStr);

                            raf.seek((long) idx * RECORD_SIZE + 23);
                            raf.write(statusByte);
                            System.out.println("Updated [" + idx + "]: " + statusStr);
                        }
                        break;

                    case "PRINT_ALL":
                        for (int i = 0; i < n; i++) {
                            printRecord(raf, i);
                        }
                        break;
                }
            }
        }
        sc.close();
    }

    private static void printRecord(RandomAccessFile raf, int idx) throws IOException {
        byte[] buffer = new byte[RECORD_SIZE];
        raf.seek((long) idx * RECORD_SIZE);
        raf.readFully(buffer);

        ByteBuffer bb = ByteBuffer.wrap(buffer).order(ByteOrder.LITTLE_ENDIAN);

        int id = bb.getInt();
        double suma = bb.getDouble();

        String data = new String(buffer, 12, 10).trim();

        String tip = (buffer[22] == 0) ? "CREDIT" : "DEBIT";

        String status = decodeStatus(buffer[23]);

        System.out.printf(Locale.US, "[%d] id=%d data=%s tip=%s suma=%.2f RON status=%s\n",
                idx, id, data, tip, suma, status);
    }

    private static byte encodeStatus(String status) {
        return switch (status) {
            case "PROCESSED" -> (byte) 1;
            case "REJECTED" -> (byte) 2;
            default -> (byte) 0;
        };
    }

    private static String decodeStatus(byte status) {
        return switch (status) {
            case 1 -> "PROCESSED";
            case 2 -> "REJECTED";
            default -> "PENDING";
        };
    }
}
