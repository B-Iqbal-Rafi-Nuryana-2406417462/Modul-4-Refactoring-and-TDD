Exercise 1

Prinsip clean code diterapkan dalam beberapa hal
1. Penamaan yang jelas dan deskriptif untuk variabel, fungsi, dan kelas.
2. Fungsi yang pendek dan melakukan satu tugas spesifik.
3. Struktur proyek yang terorganisir dengan baik(controller,model,repository,service).

Exercise 2

1. Setelah membuat unit test, saya menjadi lebih yakin bahwa kode yang saya tulis berfungsi sesuai harapan.
Unit test membantu mendeteksi bug lebih awal dan memastikan bahwa perubahan kode di masa depan tidak merusak fungsionalitas yang sudah ada.
Jumlah unit test biasanya disesuikan dengan banyaknya fungsi atau metode yang ada dalam kode.
Jika saya punya code coverage 100%, saya merasa sangat percaya diri bahwa kode saya telah diuji secara menyeluruh dan kemungkinan besar bebas dari bug.
2. - Membuat abstract base test class
- Memindahkan setup umum ke sana
- Lalu setiap test suite (misalnya CreateProductFunctionalTest dan HomePageFunctionalTest) extends base class tersebut
- Dengan begitu, kode setup tidak perlu diulang di setiap test suite, sehingga mengurangi duplikasi kode dan membuatnya lebih mudah untuk dikelola.

Exercise 2

1. Code quality issues fixed:
- Mengganti beberapa parameter yang tidak pernah di ubah menjadi final
- Method memiliki terlalu banyak return statement.
- Mengganti beberapa nama variabel menjadi lebih pendek
- Menghapus modifier yang tidak diperlukan pada beberapa method 
- Menambahkan utility class warning
2. CI/CD implementations based on definitions
- Continuous Integration (CI): Setiap push/pull-request ke setiap branch akan trigger otomatis ci.yml yang akan menjalankan unit test dan PMD static analysis, untuk memastikan kode selalu terferifikasi sebelum melakukan merging.
- Continuous Development (CD): Setelah branch di merge ke main, KOYEB akan melakukan redeployment otomatis, sehingga setiap perubahan yang sudah di merge akan langsung diterapkan ke production tanpa perlu melakukan manual deployment.
- Full Pipeline: Workflows mencakup build -> test -> code quality check -> deploy, sehingga memastikan setiap perubahan kode melewati proses yang ketat sebelum diterapkan ke production.

Exercise 3
1. -SRP : Sekarang setiap class punya  tugas spesifik, misalnya CarRepostory hanya bertanggung jawab tentang operasi database untuk Car, CarServiceImpl hanya bertanggung jawab untuk logika bisnis terkait Car, dan CarController hanya bertanggung jawab untuk handle Car HTTP request. Lalu, saya juga memisahkan beberapa fungsi seperti IdGenerator dan IteratorToList menjadi class tersendiri.
- OCP : Saya menambahkan beberapa abstract class dan interface. Misalnya AbstractInMemoryRepository dan AbstractBaseServiceImpl yang bisa di extend oleh class lain, sehingga jika ingin menambahkan entitiy baru seperti Motor, kita hanya perlu membuat MotorRepository dan MotorServiceImpl yang extend class tersebut tanpa perlu mengubah kode yang sudah ada.
- LSP : Saya memastikan bahwa setiap subclass bisa menggantikan superclass tanpa merusak fungsionalitas. Misalnya, CarServiceImpl dan ProductServiceImpl sama-sama extend AbstractBaseServiceImpl<T> dan implement masing-masing interface. Controller bisa menggunakan CarService atau ProductService interface tanpa perlu tahu implementasinya, sehingga jika nanti ingin mengganti implementasi, kita hanya perlu membuat class baru yang extend AbstractBaseServiceImpl<T> dan implement interface yang sama.
- ISP : Saya memecah beberapa interface menjadi lebih kecil dan spesifik. Misalnya, CarService dan ProductService extend BaseInterface<T> dan hanya declare methods yang relevan. Controller hanya bergantung pada interface yang mereka butuhkan, sehingga tidak perlu tahu tentang method yang tidak relevan.
- DIP : Saya memastikan bahwa class tingkat tinggi (Controller) tidak bergantung pada class tingkat rendah (Repository), melainkan keduanya bergantung pada abstraksi (Service Interface). Service Interface juga tidak bergantung pada implementasi konkret, sehingga kita bisa dengan mudah mengganti implementasi jika diperlukan tanpa merubah kode di Controller.
2. Advantages dari penerapan SOLID principles:
- SRP : Lebih mudah untuk maintenance dan debugging.
- OCP : Lebih mudah untuk menambahkan fitur baru tanpa merubah kode yang sudah ada.
- DIP : Lebih mudah untuk melakukan testing karena kita bisa menggunakan mock atau stub untuk service interface, tanpa perlu bergantung pada implementasi konkret.
- LSP : Meningkatkan fleksibilitas dan reusability kode.
- ISP : Mengurangi ketergantungan pada method yang tidak relevan, sehingga membuat kode lebih mudah untuk dipahami dan digunakan.
3. Disadvantages jika tidak menerapkan SOLID principles:
- SRP : Kode menjadi sulit untuk dipahami dan di-maintain karena satu class memiliki banyak tanggung jawab.
- OCP : Menambahkan fitur baru bisa merusak kode yang sudah ada, sehingga meningkatkan risiko bug.
- LSP : Mengganti subclass bisa merusak fungsionalitas jika subclass tidak benar-benar menggantikan superclass, sehingga membuat kode menjadi tidak stabil.
- ISP : Controller bisa bergantung pada method yang tidak relevan, sehingga membuat kode menjadi sulit untuk dipahami dan digunakan.
- DIP : Controller bergantung pada implementasi konkret, sehingga sulit untuk melakukan testing dan maintenance karena setiap perubahan pada implementasi konkret bisa mempengaruhi Controller.

Reflection 4
1. Alur TDD dalam latihan ini cukup berguna, namun belum sepenuhnya diterapkan. Berdasarkan pertanyaan reflektif Percival:
Yang berjalan baik:
- Menulis test seperti testUpdateStatusInvalidStatus memaksa saya berpikir tentang edge case sebelum implementasi
- Test testUpdateStatusInvalidStatus mengungkap bahwa OrderServiceImpl.updateStatus() tidak memvalidasi status. Test mengharapkan IllegalArgumentException namun service tidak melemparnya, artinya test berhasil mendeteksi fitur yang belum diimplementasi.
- Test berfungsi sebagai dokumentasi hidup tentang perilaku yang diharapkan (misal: createOrder mengembalikan null jika sudah ada)
Yang belum optimal:
- Beberapa test seperti testCreateOrder hanya memverifikasi bahwa save dipanggil sekali, tanpa menguji logika bisnis yang bermakna, mengindikasikan test ditulis setelah implementasi
- Lain kali: tulis test yang gagal terlebih dahulu, jalankan untuk konfirmasi kegagalan, baru implementasi minimal untuk membuatnya lulus, lalu refactor

    