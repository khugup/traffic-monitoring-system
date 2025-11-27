import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { RoadService, Road } from '../../services/road.service';

@Component({
  selector: 'app-road-edit',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule], // RouterModule added
  template: `
    <div *ngIf="road" class="white-card center-card">
      <h4 class="text-center">Edit Road</h4>

      <form (ngSubmit)="save()">
        <div class="mb-2">
          <label>Road Name:</label>
          <input [(ngModel)]="road.roadName" name="roadName" class="form-control" required>
        </div>

        <div class="mb-2">
          <label>Detector Name:</label>
          <input [(ngModel)]="road.detectorName" name="detectorName" class="form-control" required>
        </div>

        <div class="mb-2">
          <label>Status:</label>
          <select [(ngModel)]="road.detectorStatus" name="detectorStatus" class="form-control">
            <option [value]="true">UP</option>
            <option [value]="false">DOWN</option>
          </select>
        </div>

        <button type="submit" class="btn btn-primary">Save</button>
        <a [routerLink]="['/roads', road.id]" class="btn btn-secondary">Cancel</a>
      </form>
    </div>
  `
})
export class RoadEditComponent implements OnInit {

  road: Road | null = null;

  constructor(
    private route: ActivatedRoute,
    private roadService: RoadService,
    private router: Router
  ) {}

  ngOnInit() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.roadService.getRoadById(id).subscribe(r => this.road = r);
  }

  save() {
    if (!this.road || this.road.id === undefined) return;

    this.roadService.updateRoad(this.road.id, this.road).subscribe(() => {
      this.router.navigate(['/roads', this.road!.id]);
    });
  }
}
