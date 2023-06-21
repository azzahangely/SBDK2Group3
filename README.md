# Intro to Mapreduce




# Tutorial Instalasi Hadoop

## A. Menginstal Development Kit 8 (JDK 8) windows x64
1. Anda dapat mengklik tautan ini:
https://www.oracle.com/java/technologies/downloads/#java8 <img src="Images/installJDK.png" alt="install jdk 8">

2. Klik _agreement box_ kemudian klik **Download jdk-8u371-windows-x64.exe**

3. Anda akan diarahkan ke web Oracle untuk melakukan pengunduhan.

**_Notes:_**
- Apabila Anda sudah Sign In, maka file akan secara otomatis terunduh.
- Jika belum, Anda akan diminta untuk Sign In terlebih dahulu.
- Jika Anda belum memiliki akun Oracle, maka buatlah akun terlebih dahulu.

  <img src="Images/SignInOracle.png" alt="sign in" width="400">


4. Setelah file berhasil diunduh, buka JDK file. <img src="Images/openJDK.png" width="400">

5. Buka **environment variable** di **system properties** <img src="Images/openEnvirontment.png"  width="400">

6. Buat variabel pengguna baru bernama `JAVA_HOME` <img src="Images/createJAVA_HOME.png" width="400">

7. Atur Path Java pada System Variable, klik New dan isi dengan direktori  `jdk\bin`. <img src="Images/setPath.png"  width="400">

8. Buka cmd untuk memverifikasi instalasi <img src="Images/verifyInstallation.png"  width="400">



## B. Instalasi Hadoop
1. Kita akan menggunakan Hadoop versi 2.9.2. Link download:
https://hadoop.apache.org/release/2.9.2.html 

2. Klik **Download tar.gz** <img src="Images/downloadTar.png" width="400">

3. Pindahkan file `tar.gz` yang diunduh ke **Disk C**, kemudian ekstrak Hadoop pada **Disk C**. <img src="Images/extractTar.png" width="400">

4. Klik **close** pada _pop up window_ di bawah ini setelah ekstrak berhasil dilakukan. <img src="Images/close.png" width="400">

5. Ganti nama folder Hadoop 2.9.2 menjadi hadoop <img src="Images/renameHadoop" width="400">
6. Melakukan konfigurasi terhadap file hadoop dengan membuka folder hadoop → etc → hadoop. Kemudian buka file core-site.xml, mapred-site.xml , yarn-site.xml, hdfs-site.xml, dan hadoop-env.cmd di text editor. 
7. Menambahkan kode pada core-site.xml <img src="Images/config-core-site.png" width="400">
8. Menambahkan kode pada mapred-site.xml <img src="Images/config-core-site.png" width="400">
9. Menambahkan kode pada file  yarn-site.xml <img src="Images/config-core-site.png" width="400">
10. Membuat 2 folder baru dengan nama datanode dan namenode. <img src="Images/createnewfolder.png" width="400"> 
11. Menambahkan kode pada hdfs-site.xml <img src="Images/config-hdfs-site.png" width="400"> 
12. Menyesuaikan direktori JAVA_HOME dengan direktori java jdk pada file hadoop-env.cmd  <img src="Images/config-hdfs-site.png" width="400"> 
13. Mengatur environment variable pada hadoop  <img src="Images/config-hdfs-site.png" width="400"> 
14. Create user variable bernama HADOOP_HOME dengan value bernama hadoop\bin.  <img src="Images/config-hdfs-site.png" width="400"> 
15. Mengatur path hadoop pada system variable dengan mengisi direktori hadoop\bin dan hadoop\sbin  <img src="Images/config-hdfs-site.png" width="400"> 
16. Mendownload path file hadoop agar dapat berjalan pada windows, link: https://github.com/cdarlint/winutils/tree/master/hadoop-2.9.2/bin  <img src="Images/config-hdfs-site.png" width="400"> 
17. Ekstrak file tersebut, lalu ubah folder bin pada hadoop menjadi folder yang telah di ekstrak tersebut  <img src="Images/config-hdfs-site.png" width="400"> 


# Pembandingan waktu eksekusi wordcount biasa dengan hadoop

## A. Langkah-langkah percobaan
1. Mempersiapkan file text berukuran 1 MB, 10 MB, 100 MB, 500 MB, dan 1 GB
File file diatas dapat dicari di link :
- http://textfiles.com/etext/FICTION/alcott-little-261.txt
- https://www.i3s.unice.fr/~jplozi/hadooplab_lsds_2015/datasets/

2. Membuat kode  `SimpleWordCount.java`


3. Mengcompile program java SimpleWordCount

```
javac /namaFileWordCount.java
```



4. Mengukur kecepatan eksekusi program java
```
measure-command{java [compiled java] [path_to_input]}
```
![Count](Images/cmdno4.jpg)

5. Lakukan langkah 2 sampai 4 untuk berbagai ukuran file

6. Menyalakan hadoop dengan command `start-all.cmd.`
note: run as administrator, pindah direktori ke hadoop/sbin

7. Membuat direktori tempat input
```
hadoop fs -mkdir [nama_direktori]
```

8. Menyimpan file input ke direktori
```
hadoop fs -put [path_to_input] [nama_direktori]
```

9. Menjalankan wordcount hadoop
```
hadoop jar [path to snapshot.JAR] [main_class name] [input_directory] [output_directory]
```

10. Menjalankan command 7 - 9. Command no 8 diganti dengan:
```
hdfs dfs -D dfs.blocksize=[Ukuran blok dalam satuan byte] -put [path_to_input.txt] [inputFolder]
```

## B. Hasil (Tabel dan Grafik)
![Tabel](Images/tabel.jpg)

![Grafik](Images/grafik.jpg)



## C. Analysis

Pada percobaan SimpleWordCount, durasi eksekusi input berukuran 1MB, 10MB dan 100MB tidak banyak berbeda karena ketiga file tersebut memiliki keragaman kata yang rendah sehingga tidak banyak melakukan operasi tambahan untuk menambahkan kata baru ke daftar. Sementara file berukuran 500MB dan 1GB memiliki keragaman kata yang tinggi sehingga memerlukan waktu tambahan untuk menambahkan kata baru ke daftar (untuk mengalokasikan memory)

Pada percobaan hadoop dengan blok berukuran 128 dan blok berukuran 256 terdapat perbedaan durasi eksekusi pada input berukuran 1GB. Hal ini karena tiap blok  merupakan aplikasi tersendiri dengan jvm tersendiri yang membutuhkan ruang memory. Karena keseluruhan percobaan dijalankan di sistem tunggal, jumlah blok yang sedikit berarti aplikasi yang sedikit sehingga tidak banyak overhead untuk menciptakan process jvm tersendiri. Sementara itu, untuk input file berukuran 500MB dan dibawah nya tidak banyak perbedaan karena perangkat memiliki cukup RAM untuk menjalankan beberapa process wordcount secara concurrent. 

Pada keseluruhan percobaan, program hadoop membutuhkan waktu yang lebih lama. Hal ini disebabkan oleh banyaknya pekerjaan yang dilakukan oleh hadoop. Pekerjaan-pekerjaan tersebut diantaranya,menginisiasi resource, melakukan mapping, split lalu proses wordcount (reduce). Sementara program SimpleWordCount berjalan lebih cepat karena tidak ada proses mapping dan split. Hal ini menunjukkan bahwa hadoop cocok digunakan untuk menangani data berukuran sangat besar yang tersimpan secara terdistribusi. Sementara program wordcount cocok digunakan pada sistem tunggal dengan data berukuran kecil sampai berukuran beberapa gigabyte
