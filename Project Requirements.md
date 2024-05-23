# Java Spring Boot - Veteriner Yönetim Sistemi Bitirme Projesi
## Veteriner Yönetim Sistemi
Veteriner yönetim sistemi projesi ile bir veteriner kliniğinin kendi işlerini yönetebildiği API yazmanı istiyoruz.

Yazacağınız uygulama veteriner çalışanı tarafından kullanılacaktır. Bu uygulama ile çalışan sisteme

veteriner doktorları kaydedecek,

doktorların çalışma günlerini (müsait günlerini) kaydedecek, saat olmadan tarih olarak kayıt yapılacak,

müşterileri kaydedecek,

müşterilere ait hayvanları kaydedecek,

hayvanlara uygulanmış aşıları tarihleriyle birlikte kaydedecek,

hayvanlar için veteriner hekimlere randevu oluşturacaklar,

randevu oluştururken tarih ve saat girilecek,

randevu oluştururken hem doktorun müsait günlerinden saat olmadan kontrol yapılmalı hem de randevu kayıtlarından tarih ve saat ile kontrol yapılmalı. Kayıtlarda çakışma olmadığı durumda randevu oluşturulmalıdır.

Projede Bulunan Entityler

Animal

- id:Long

- name:String

- species:String

- breed:String

- gender:String

- colour:String

- dateOfBirth:LocalDate

Customer

- id:Long

- name:String

- phone:String

- mail: String

- address:String

- city:String

Vaccine

- id: Long

- name: String 

- code: String

- protectionStartDate: LocalDate

- protectionFinishDate: LocalDate

Doctor

- id:Long

- name:String

- phone:String

- mail: String

- address:String

- city:String

AvailableDate

- id:Long

- availableDate:LocalDate

Appointment

- id:Long

- appointmentDate:LocalDateTime

API Temel Özellikleri
Hayvanların ve Sahiplerinin (customer) Yönetimi

Hayvanları kaydetme, bilgilerini güncelleme, görüntüleme ve silme

Hayvan sahiplerini kaydetme, bilgilerini güncelleme, görüntüleme ve silme

Hayvan sahipleri isme göre filtrelenecek şekilde end point oluşturmak.

Hayvanlar isme göre filtrelenecek şekilde end point oluşturmak.

Hayvan sahibinin sistemde kayıtlı tüm hayvanlarını görüntülemek için API end point'ini oluşturmak. Hayvan sahibine göre hayvanlara filtreleme yapmalısın.

Uygulanan Aşıların Yönetimi

Hayvanlara uygulanan aşıları kaydetme, bilgilerini güncelleme, görüntüleme ve silme

Eğer hastaya ait aynı tip aşının (adı ve kodu aynı olan aşı) aşı koruyuculuk bitiş tarihi daha gelmemiş ise sisteme yeni aşı girilememelidir. Aşı kodlarından ve aşı bitiş tarihlerinden bunu kontrol edebilirsin.

Hayvan id’sine göre belirli bir hayvana ait tüm aşı kayıtlarını listelemek için gerekli API end point'ini oluşturmak.

Kullanıcının aşı koruyuculuk bitiş tarihi yaklaşan hayvanları listeleyebilmesi için gireceği başlangıç ve bitiş tarihlerine göre aşı koruyuculuk bitiş tarihi bu aralıkta olan aşıları hayvan bilgileriyle birlikte listesini geri döndüren API end point'ini oluşturmak.

Randevu Yönetimi

Hayvanların aşı ve muayene randevularının oluşturulması, bilgilerinin güncellenmesi, görüntülenmesi ve silinmesi

Randevular sisteme tarih ve saat içerecek şekilde kaydedilmelidir. Bunun için LocalDateTime kullanılmalıdır.

Hayvanların her türlü muayenesi için doktorlardan uygun tarihlerde ve saatlerde randevu oluşturulmalıdır. Her doktor için sadece saat başı randevu oluşturulabilir. Bir muayenenin sabit olarak bir saat süreceğini kabul edin.

Randevu kaydı oluştururken doktorun girilen tarihte müsait günü olup olmadığı eğer ki müsait günü varsa randevu kayıtlarında girilen saatte başka bir randevusu olup olmadığı kontrol edilmelidir. Her iki durum şartı sağlanırsa randevu oluşturulmalıdır. Şart sağlanmaz ise "Doktor bu tarihte çalışmamaktadır!/Girilen saatte başka bir randevu mevcuttur." gibi hata mesajı fırlatılmalıdır. Bunun için custom exception oluşturmalısın.

Randevular kullanıcı tarafından girilen tarih aralığına ve doktora göre filtrelenmelidir. Buna ait API end point’i oluşturulmalıdır. (randevu için kliniği arayan müşterilerin doktor ve gün taleplerinde uygunluk olup olmadığını sorgulamak için kullanılacaktır.) Jpa’nın findBy between kullanımına bakabilirsin.

Randevular kullanıcı tarafından girilen tarih aralığına ve hayvana göre filtrelenmelidir. Buna ait API end point’i oluşturulmalıdır. Jpa’nın findBy between kullanımına bakabilirsin.

Veteriner Doktor Yönetimi

Veteriner doktorların kaydedilmesi, bilgilerinin güncellenmesi, görüntülenmesi ve silinmesi

Doktorların Müsait Günlerinin Yönetimi

Doktorların müsait günlerini ekleme, bilgilerini güncelleme, görüntüleme ve silme

Doktorun çalıştığı günler sisteme LocalDate olarak kaydedilecektir. Sadece tarih bilgisi olacaktır. Saat, dakika, saniye bilgisi olmayacaktır.

Projede Dikkat Edilmesi Gereken Hususlar

Proje başlangıcında UML diyagram oluşturulmalı ve daha sonra readme dosyasına eklenmelidir.

UML diyagram oluşturmak için https://www.lucidchart.com/ adresini kullanabilirsin.

Katmanlı mimari kullanılmalıdır.

IoC, DI için constructor injection kullanılmalıdır.

Gerekli anotasyonlar (@Entity, @Table, @Id, @OneToMany, @ManyToOne, @ManyToMany vs.) yazılmalıdır.

Tüm yeni veri kaydetme işlemlerinde zaten var olan bir verinin kaydedilmediği kontrol edilmelidir. Eğer ki kayıt zaten var ise “Kayıt sistemde mevcut.” gibi hata mesajı fırlatılmalıdır. Bunun için custom exception oluşturmalısın.

Exception kullanılmalıdır. Örneğin id parametresi alınarak yapılan update,delete işlemlerinde silme isteği atmadan veri tabanında girilen id ile ilgili kayıt var mı diye kontrol edilmelidir. Eğer ki kayıt yok ise “id + " id’li kayıt sistemde bulunamadı.” gibi hata mesajı fırlatılmalıdır. Bunun için custom exception oluşturmalısın. Eğer kayıt varsa silme işlemi yapılmalıdır.

Projede belirtilen entity (varlık) sınıflarını ve bunların arasındaki ilişkiler belirlenmelidir.

Gerekli anotasyonları (@Entity, @Table, @Id, @OneToMany, @ManyToOne, @ManyToMany) yazın.

Gerekli Fetch ve Cascade anotasyonlarını yazın.

HTTP durum kodları doğru ve anlamlı olmalıdır.

Sistemde alınabilecek hatalarda API kullanıcısına anlamlı çıktılar verilmeli ve hatalar yönetilmelidir.

Request ve Response DTO’lar kullanabilirsin.

API end pointlerini anlatan bir doküman hazırlanmalıdır.

Bir veri sildikten sonra veri tabanında anlamsız veri kalmamalıdır. Örneğin : Sistemde bir Customer silindiği zaman, o Customer’a ait hayvanlar ve o hayvanlara ait bilgilerinde sistemden silinmesi gereklidir.

PostgreSQL ve MySQL veri tabanı kullanabilirsin.

Spring Web, Spring Data Jpa, PostgreSQL, MySQL dependencyleri eklenmelidir.

Ödevi teslim ederken veri tabanına her tabloya en az 5 veri kaydedin, veri tabanını dışa aktarıp .sql dosyasını da proje klasörüne ekleyin ve github’ınıza pushlayın.