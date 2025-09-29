using BanqueDepot.Data;
using BanqueDepot.Models;
using Microsoft.EntityFrameworkCore;

namespace BanqueDepot.Services
{
    public class CompteDepotService
    {
        private readonly BanqueDepotContext _context;

        public CompteDepotService(BanqueDepotContext context)
        {
            _context = context;
        }

        // Ouvrir un compte dépôt
        public async Task<CompteDepot> OuvrirCompteDepot(CompteDepot compte)
        {
            compte.Solde ??= 0;
            compte.StatusId = 1; // "actif"

            _context.ComptesDepot.Add(compte);
            await _context.SaveChangesAsync();

            return compte;
        }

        // Fermer un compte dépôt
        public async Task<CompteDepot?> FermerCompteDepot(int id)
        {
            var compte = await _context.ComptesDepot.FindAsync(id);
            if (compte == null) return null;

            compte.StatusId = 2; // Fermé
            await _context.SaveChangesAsync();
            return compte;
        }

        // Obtenir solde avec intérêts cumulés
        public async Task<decimal?> GetSoldeActuel(int id)
        {
            var compte = await _context.ComptesDepot.FindAsync(id);
            if (compte == null || compte.Solde == null) return null;

            var duree = (decimal)(DateTime.Now - compte.DateOuverture).TotalDays / 365m;
            decimal interets = compte.Solde.Value * compte.Taux * duree;
            return compte.Solde + interets;
        }

        // Obtenir le solde actuel cumulé de tous les comptes dépôt d’un client
        public async Task<decimal?> GetSoldeActuelByClient(int clientId)
        {
            var comptes = await _context.ComptesDepot
                .Where(c => c.ClientId == clientId && c.StatusId == 1) 
                .ToListAsync();

            decimal total = 0;

            foreach (var compte in comptes)
            {
                if (compte.Solde != null)
                {
                    var duree = (decimal)(DateTime.Now - compte.DateOuverture).TotalDays / 365m;
                    decimal interets = compte.Solde.Value * compte.Taux * duree;
                    total += compte.Solde.Value + interets;
                }
            }

            return total;
        }



        // Lister les comptes d’un client
        public async Task<List<CompteDepot>> GetComptesByClient(int clientId)
        {
            return await _context.ComptesDepot
                .Where(c => c.ClientId == clientId)
                .Include(c => c.Status)
                .ToListAsync();
        }
    }
}
