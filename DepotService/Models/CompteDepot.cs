using System.ComponentModel.DataAnnotations;
<<<<<<< Updated upstream
using System.ComponentModel.DataAnnotations.Schema;
using System.Text.Json.Serialization;
=======
using System.Text.Json.Serialization;
using BanqueDepot.Models;
>>>>>>> Stashed changes

namespace BanqueDepot.Models
{
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

<<<<<<< Updated upstream
        [Required]
        public int StatusId { get; set; }

        public Status Status { get; set; }

        [Required]
        public int ClientId { get; set; }

        [NotMapped]
        public Client Client { get; set; }
    }
    
=======
    [Required]
    public int StatusId { get; set; }

    [JsonIgnore]  
    public Status Status { get; set; }

    [Required]
    public int ClientId { get; set; }

    [JsonIgnore] 
    public Client Client { get; set; }
>>>>>>> Stashed changes
}
