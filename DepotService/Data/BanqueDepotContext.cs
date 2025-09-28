using BanqueDepot.Models;
using Microsoft.EntityFrameworkCore;

namespace BanqueDepot.Data
{
    public class BanqueDepotContext : DbContext
    {
        public BanqueDepotContext(DbContextOptions<BanqueDepotContext> options)
            : base(options) { }

        // public DbSet<Client> Clients { get; set; }
        public DbSet<Status> Statuses { get; set; }
        public DbSet<CompteDepot> ComptesDepot { get; set; }
    }
}
