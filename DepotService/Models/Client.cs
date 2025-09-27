using System.ComponentModel.DataAnnotations;

namespace BanqueDepot.Models
{
    public class Client {
        [Key]
        public int Id { get; set; }

        [Required, StringLength(50)]
        public string Nom { get; set; }
    }
}
