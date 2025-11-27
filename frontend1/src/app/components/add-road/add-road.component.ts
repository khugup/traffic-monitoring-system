import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormGroup, FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RoadService } from '../../services/road.service';

@Component({
  selector: 'app-add-road',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './add-road.component.html',
  styleUrls: ['./add-road.component.css']
})
export class AddRoadComponent {

  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private roadService: RoadService,
    private router: Router
  ) {
    this.form = this.fb.group({
      name: ['', Validators.required],
      detector: ['', Validators.required],
      status: ['UP', Validators.required]
    });
  }

  submit() {
    if (this.form.invalid) {
      alert('Please fill all fields.');
      return;
    }

    const road = {
      roadName: this.form.value.name,
      detectorName: this.form.value.detector,
      detectorStatus: this.form.value.status === 'UP'
    };

    this.roadService.addRoad(road).subscribe(() => {
      alert('Road added successfully!');
      this.router.navigate(['/roads']);
    });
  }
}
