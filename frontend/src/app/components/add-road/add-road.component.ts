import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { FormsModule, ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { RoadService, Road } from '../../services/road.service';

@Component({
  selector: 'app-add-road',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './add-road.component.html'
})
export class AddRoadComponent {

  form: any;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private roadService: RoadService
  ) {
    this.form = this.fb.group({
      name: ['', Validators.required],
      detector: ['', Validators.required],
      status: ['UP', Validators.required]
    });
  }

  submit() {
    if (this.form.invalid) return;

    const payload: Road = {
      roadName: this.form.value.name,
      detectorName: this.form.value.detector,
      detectorStatus: this.form.value.status === 'UP' // map string to boolean
    };

    this.roadService.addRoad(payload).subscribe({
      next: () => this.router.navigate(['/roads']),
      error: (err) => console.error("SAVE FAILED:", err)
    });
  }
}
