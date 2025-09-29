using BanqueDepot.Models;
using BanqueDepot.Services;
using Microsoft.AspNetCore.Mvc;

namespace BanqueDepot.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class CompteDepotController : ControllerBase
    {
        private readonly CompteDepotService _service;

        public CompteDepotController(CompteDepotService service)
        {
            _service = service;
        }

        [HttpPost("ouvrir")]
        public async Task<IActionResult> OuvrirCompteDepot([FromBody] CompteDepot compte)
        {
            var result = await _service.OuvrirCompteDepot(compte);
            return Ok(result);
        }

        [HttpPost("fermer/{id}")]
        public async Task<IActionResult> FermerCompteDepot(int id)
        {
            var result = await _service.FermerCompteDepot(id);
            if (result == null) return NotFound();
            return Ok(result);
        }

        [HttpGet("solde/{id}")]
        public async Task<IActionResult> GetSolde(int id)
        {
            var solde = await _service.GetSoldeActuel(id);
            if (solde == null) return NotFound();
            return Ok(new { soldeActuel = solde });
        }

        [HttpGet("client/{clientId}")]
        public async Task<IActionResult> GetComptesClient(int clientId)
        {
            var comptes = await _service.GetComptesByClient(clientId);
            return Ok(comptes);
        }

        [HttpGet("client/solde/{clientId}")]
        public async Task<IActionResult> GetSoldeActuelByClient(int clientId)
        {
            var solde = await _service.GetSoldeActuelByClient(clientId);
            if (solde == null) return NotFound();
            return Ok(new { soldeActuel = solde });
       }
    }
}
