using System.ComponentModel.DataAnnotations;

namespace BanqueDepot.Models
{
    public class Status
    {
        [Key]
        public int Id { get; set; }

        [Required, StringLength(50)]
        public string Description { get; set; }
    }
}
