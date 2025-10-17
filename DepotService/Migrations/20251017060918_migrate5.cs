using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace DepotService.Migrations
{
    /// <inheritdoc />
    public partial class migrate5 : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_ComptesDepot_Statuses_StatusId",
                table: "ComptesDepot");

            migrationBuilder.DropIndex(
                name: "IX_ComptesDepot_StatusId",
                table: "ComptesDepot");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateIndex(
                name: "IX_ComptesDepot_StatusId",
                table: "ComptesDepot",
                column: "StatusId");

            migrationBuilder.AddForeignKey(
                name: "FK_ComptesDepot_Statuses_StatusId",
                table: "ComptesDepot",
                column: "StatusId",
                principalTable: "Statuses",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }
    }
}
