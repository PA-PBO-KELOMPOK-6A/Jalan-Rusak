# PROGRAM APLIKASI LAPOR JALAN RUSAK DI SAMARINDA
Kelompok 6A
1. Alyssa Dwiana Mulia 2309116009
2. Siti Annida Adzra 2309116013
3. Widia Saputri 2309116019
4. Fitriani 2309116021

## 📍Penjelasan Aplikasi Pelaporan dan Monitoring Jalan Rusak di Kota Samarinda

Aplikasi Pelaporan dan Monitoring Jalan Rusak di Kota Samarinda adalah perangkat lunak berbasis Java yang dirancang untuk membantu masyarakat melaporkan kondisi jalan rusak serta memfasilitasi Dinas Pekerjaan Umum (DPU) dalam memantau dan mengelola laporan tersebut secara efisien. Menggunakan teknologi Java Swing untuk antarmuka user (GUI) dan MySQL sebagai basis data, aplikasi ini menyediakan platform yang terstruktur untuk mencatat, menyimpan, dan mengelola informasi terkait kerusakan jalan.

Tujuan utama aplikasi ini adalah menjadi sarana efektif bagi masyarakat Kota Samarinda untuk melaporkan kondisi jalan rusak, sekaligus mempermudah Dinas Pekerjaan Umum dalam mengelola laporan dengan lebih cepat, terorganisir, dan transparan. Dengan aplikasi ini, proses perbaikan jalan diharapkan dapat dilakukan secara lebih efisien, meningkatkan kualitas pelayanan publik dalam infrastruktur jalan.

### Fitur Utama Aplikasi

#### 1. Login dan Registrasi
     -> Login: User (admin atau pelapor) dapat masuk ke aplikasi dengan memasukkan username dan password. Aplikasi akan memverifikasi kredensial dan mengarahkan user ke menu sesuai peran mereka.
     -> Registrasi: Calon pelapor yang belum memiliki akun dapat mendaftarkan diri dengan memasukkan data pribadi, seperti nama, email, dan nomor telepon serta membuat username dan password.

#### 2. Menu Admin
      Admin dapat:
      -> Melihat laporan: Admin dapat menampilkan daftar laporan jalan rusak. 
      -> Filter laporan: Admin dapat memfilter laporan berdasarkan status (belum diperbaiki, sedang diperbaiki, selesai) dan "semua" untuk menampilkan semua data.
      -> Searching: Admin dapat mencari laporan berdasarkan nama jalan.
      -> Mengubah status laporan: Admin memiliki akses untuk mengedit status laporan dengan status "Belum diproses", "Sedang diproses", dan "Selesai" untuk mencerminkan progres perbaikan.

#### 3. Menu Pelapor
      Pelapor dapat:
      -> Membuat laporan baru: Pelapor memasukkan informasi seperti nama jalan, kecamatan, deskripsi kerusakan, dan tingkat kerusakan (ringan, sedang, berat) dan "semua" untuk menampilkan semua data.
      -> Melihat laporan: Pelapor dapat melihat laporan mereka sendiri.
      -> Filter laoran: Pelapor dapat memfilter laporan berdasarkan tingkat kerusakan.
      -> Searching: Pelapor dapat mencari laporan tertentu berdasarkan nama jalan.

#### 4. Manajemen Data
      -> Aplikasi menggunakan database MySQL untuk menyimpan informasi user, laporan, dan data pendukung lainnya. Data dapat diakses dan diperbarui secara real-time melalui antarmuka aplikasi.

#### 5. Teknologi yang Digunakan
      -> Aplikasi ini dibuat menggunakan Java Swing untuk tampilan antarmuka (GUI) dan MySQL sebagai tempat penyimpanan data. Jadi, semua data laporan dan akun user disimpan dengan rapi.

### Manfaat Aplikasi
   #### Bagi Masyarakat:
      -> Mempermudah pelaporan jalan rusak tanpa harus mendatangi kantor DPU.
      -> Memberikan akses transparansi terhadap progres perbaikan laporan.

   #### Bagi Dinas Pekerjaan Umum (DPU/Admin):
      -> Mengorganisir laporan secara terstruktur.
      -> Mempercepat proses pengambilan keputusan untuk perbaikan jalan.
      -> Menyediakan data analitik untuk evaluasi infrastruktur.

Aplikasi ini diharapkan bisa menjadi solusi untuk memperbaiki infrastruktur jalan di Samarinda, sekaligus membantu mengurangi risiko kecelakaan akibat jalan rusak.


## 📍Penerapan 4 Pilar
### 1. Encapsulation
   -> Menyembunyikan data objek dan hanya mengizinkan akses melalui metode khusus (getter/setter), untuk melindungi data tersebut.

   Penerapan:
   
#### a. Pada class UserModel


       public String getUsername() {
        return username;
         }


       public void setUsername(String username) {
        this.username = username;
         }


       public String getPassword() {
        return password;
         }


       public void setPassword(String password) {
        this.password = password;
         }


#### b. Pada class Pelapor
   

         public String getNamaLengkap() {
             return namaLengkap;
         }

         public void setNamaLengkap(String namaLengkap) {
             this.namaLengkap = namaLengkap;
         }

         public String getNoTelp() {
             return noTelp;
         }

         public void setNoTelp(String noTelp) {
             this.noTelp = noTelp;
         }

         public String getEmail() {
             return email;
         }

         public void setEmail(String email) {
             this.email = email;
         }


#### c. Pada class Admin

           public String getJabatan() {
             return jabatan;
             }
     
           public void setJabatan(String jabatan) {
             this.jabatan = jabatan;
             }



   
### 2. Abstract
   -> Menyederhanakan antarmuka dengan hanya menampilkan informasi penting dan menyembunyikan detail implementasi.
   
#### Penerapan pada class UserModel
   
        public abstract class UserModel {
         protected String username;
         protected String password;
         
         public abstract ArrayList<Object[]> viewData();
        }

   
### 3. inheritance
   -> Memungkinkan kelas anak mewarisi atribut dan metode dari kelas induk, sehingga kode bisa digunakan kembali.
   
   Penerapan:
   
#### Superclass: 
##### UserModel

        public abstract class UserModel {
         protected String username;
         protected String password;
        }

#### Subclass:
##### Pelapor

        public class Pelapor extends UserModel {
         private String namaLengkap;
         private String noTelp;
         private String email;
         private Connection conn;
     
         public Pelapor(String username, String password, String namaLengkap, String noTelp, String email) {
             super(username, password);
             this.namaLengkap = namaLengkap;
             this.noTelp = noTelp;
             this.email = email;
         }

##### Admin

        public class Admin extends UserModel {
         private String jabatan;
         private Connection conn;
     
         public Admin(String username, String password, String jabatan) {
             super(username, password);
             this.jabatan = jabatan;
         }
     
### 4. polymorphism
   -> Memungkinkan objek dari kelas yang berbeda untuk merespons metode yang sama dengan cara yang berbeda, tergantung jenis objeknya.

#### Penerapan pada class UserModel

            @Override
         public ArrayList<Object[]> viewData() {
         ArrayList<Object[]> laporanList = new ArrayList<>();
             conn = Database.connect(); 
             try {
                 // SQL query to retrieve data
                 String query = "SELECT pelapor.nama_lengkap, pelapor.email, laporan.nama_jalan, laporan.kecamatan, laporan.tanggal_laporan, laporan.deskripsi, laporan.tingkat_kerusakan, laporan.status_laporan " +
                                "FROM pelapor " +
                                "JOIN laporan ON pelapor.id_user = laporan.id_user";
     
                 // Prepare the statement
                 PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery(); 
     
                 while (rs.next()) { 
                     Object[] laporan = new Object[8];
                     laporan[0] = rs.getString("nama_lengkap");
                     laporan[1] = rs.getString("email");
                     laporan[2] = rs.getString("nama_jalan");
                     laporan[3] = rs.getString("kecamatan");
                     laporan[4] = rs.getTimestamp("tanggal_laporan");
                     laporan[5] = rs.getString("deskripsi");
                     laporan[6] = rs.getString("tingkat_kerusakan");
                     laporan[7] = rs.getString("status_laporan");
                     laporanList.add(laporan); 
                 }
     
                 rs.close();
                 stmt.close(); 
     
             } catch (Exception e) {
                 e.printStackTrace();
             } finally {
                 try {
                     if (conn != null && !conn.isClosed()) {
                         conn.close();
                     }
                 } catch (Exception ex) {
                     ex.printStackTrace();
                 }
             }
             return laporanList;
         }

   
## 📍Screenshoot Output

1. Tampilan Login
   
   ![image](https://github.com/user-attachments/assets/9bf9ead7-59e7-416e-91b2-3c3dced71f9c)

   Tampilan di atas merupakan tampilan login dimana user dan admin dapat memasukkan username dan password yang mereka miliki.

2. Tampilan Registrasi
   
   ![image](https://github.com/user-attachments/assets/4f76b446-468e-42be-8a52-a822f9cfe99c)
   
   Tampilan diatas merupakan tampilan registrasi untuk pelapor yang tidak memiliki akun maka akan diminta untuk daftar atau registrasi, dimana pelapor memasukkan data dirinya. 
   
3. Tampilan Menu Admin
   
   ![image](https://github.com/user-attachments/assets/11ed16f3-0a57-4bd4-8bcc-d8ea69c233b1)

   Tampilan menu admin ini diberikan akses untuk mengelola laporan yang masuk melalui dua opsi utama, yaitu Lihat Laporan dan Edit Status Laporan. Setiap opsi dirancang untuk membantu admin dalam memantau dan memperbarui data laporan jalan rusak. Selain itu, admin juga memiliki opsi untuk kembali dari halaman jika tugas telah selesai.

4. Tampilan Menu Edit Status Laporan
   
    ![image](https://github.com/user-attachments/assets/eb39cfda-83fd-47e4-8ef2-64deb3dfac3e)

   Tampilan Edit Status Laporan, admin dapat memilih laporan tertentu dari daftar laporan yang sudah ada untuk diperbarui. Setelah laporan dipilih, aplikasi akan memberikan pilihan untuk mengubah status laporan sesuai kebutuhan, seperti dari "belum diproses" menjadi "sedang diproses" atau "selesai". Perubahan status ini akan disimpan ke dalam database dan menjadi rekam jejak laporan yang dapat dilihat kembali kapan saja oleh admin.
   
5. Tampilan Menu Lihat Laporan Admin
    
  ![image](https://github.com/user-attachments/assets/34ce0df6-ffb5-4acb-960d-1ab663902db2)

Tampilan ini memberikan transparansi kepada pelapor untuk mengetahui laporan apa saja yang sudah ada dalam Aplikasi dan mempermudah mereka memantau laporan secara menyeluruh. 
  
6. Tampilan Menu Pelapor
    
    ![image](https://github.com/user-attachments/assets/357fe79f-4074-4e46-8d40-0936d0cb0930)

   Tampilan menu pelapor ini adalah menu milik pelapor yang sudah berhasil masuk ke aplikasi, , pelapor akan diberikan dua pilihan utama, yaitu Buat Laporan dan Lihat Laporan. Kedua pilihan ini dirancang untuk mempermudah pelapor dalam membuat laporan jalan rusak dan memantau laporan yang sudah diajukan. Selain itu, pelapor juga dapat memilih untuk keluar dari aplikasi setelah selesai menggunakan aplikasi.

7. Tampilan Menu Buat Laporan
    
    ![image](https://github.com/user-attachments/assets/6f32a68e-3c9d-4822-9fda-1867df04565c)

    Jika pelapor memilih Buat Laporan, aplikasi akan meminta user untuk mengisi detail laporan, seperti nama jalan, kecamatan, tingkat kerusakan jalan, dan deskripsi. Setelah data diinput, laporan akan disimpan ke dalam database. 
   
8. Tampilan Menu Lihat Laporan Pelapor
    
    ![image](https://github.com/user-attachments/assets/e06f5a14-6122-471b-8941-ca4f104f2f18)

9. Tampilan Filter Sesuai Tingkat Kerusakan

    ![image](https://github.com/user-attachments/assets/4f1c431e-5bd2-4f25-a989-2065905c04cf)

    Tampilan fitur filter ini berfungsi untuk menyaring laporan berdasarkan tingkat kerusakan, jadi si pelapor ini dapat memilih laporan yang sudah ada berdasarkan tingkat kerusakannya ada berat, sedang, dan ringan.


10. Tampilan Searching Nama Jalan
    
    ![image](https://github.com/user-attachments/assets/00441622-3794-45e4-9edf-742cfe441586)
    
    Pada tampilan fitur searching ini pelapor dapat mencari sesuai dengan nama jalan yang dilaporkan bukan hanya dari filter saja jadi pelapor dapat searching menggunakan nama jalan rusak.

    

