using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace BanqueDepot.Models
{
    [NotMapped]
    public class Client
    {
        [Key]
        public int Id { get; set; }

        [Required, StringLength(50)]
        public string Nom { get; set; }
    }
}
