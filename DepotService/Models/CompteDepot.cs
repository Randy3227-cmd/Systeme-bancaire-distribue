using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Text.Json.Serialization;
using Microsoft.EntityFrameworkCore; 

namespace BanqueDepot.Models
{
    [Index(nameof(Numero), IsUnique = true)] 
    public class CompteDepot
    {
        [Key]
        public int Id { get; set; }

        [Required, StringLength(50)]
        public string Numero { get; set; }

        public decimal? Solde { get; set; }

        [Required]
        public decimal Taux { get; set; }

        [Required]
        public DateTime DateOuverture { get; set; }

        [Required]
        public DateTime DateEcheance { get; set; }

        [Required]
        public int StatusId { get; set; }

        [NotMapped]
        [JsonIgnore]
        public Status? Status { get; set; }

        [Required]
        public int ClientId { get; set; }

        [NotMapped]
        [JsonIgnore]
        public Client? Client { get; set; }
    }
}
