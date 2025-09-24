using DepotService.Data;
using DepotService.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace DepotService.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class DepotController : ControllerBase
    {
        private readonly DepotContext _context;

        public DepotController(DepotContext context)
        {
            _context = context;
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<DepotAccount>> GetDepot(int id)
        {
            var depot = await _context.Depots.FindAsync(id);
            if (depot == null) return NotFound();
            return depot;
        }

        [HttpPost]
        public async Task<ActionResult<DepotAccount>> CreateDepot(DepotAccount depot)
        {
            _context.Depots.Add(depot);
            await _context.SaveChangesAsync();
            return CreatedAtAction(nameof(GetDepot), new { id = depot.Id }, depot);
        }
    }
}
