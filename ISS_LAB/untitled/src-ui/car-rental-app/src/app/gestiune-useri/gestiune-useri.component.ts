import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { UtilizatorService } from '../service/utilizator.service';

@Component({
  selector: 'app-gestiune-useri',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './gestiune-useri.component.html',
  styleUrls: ['./gestiune-useri.component.css']
})
export class GestiuneUseriComponent implements OnInit {
  utilizatori: any[] = [];
  utilizatorForm: FormGroup;
  editMode = false;
  currentUserId: number | null = null;

  constructor(private utilizatorService: UtilizatorService, private fb: FormBuilder) {
    this.utilizatorForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      parola: ['', Validators.required],
      type: ['CLIENT', Validators.required]
    });
  }

  ngOnInit(): void {
    this.loadUtilizatori();
  }

  loadUtilizatori(): void {
    this.utilizatorService.getUtilizatori().subscribe({
      next: (data) => this.utilizatori = data,
      error: (err) => console.error('Eroare la incarcarea utilizatorilor', err)
    });
  }

  onSubmit(): void {
    if (this.utilizatorForm.valid) {
      if (this.editMode && this.currentUserId !== null) {
        this.utilizatorService.updateUtilizator(this.currentUserId, this.utilizatorForm.value).subscribe({
          next: () => {
            this.loadUtilizatori();
            this.resetForm();
          },
          error: (err) => console.error('Eroare la actualizarea utilizatorului', err)
        });
      } else {
        this.utilizatorService.addUtilizator(this.utilizatorForm.value).subscribe({
          next: () => {
            this.loadUtilizatori();
            this.resetForm();
          },
          error: (err) => console.error('Eroare la adaugarea utilizatorului', err)
        });
      }
    }
  }

  editUtilizator(user: any): void {
    this.editMode = true;
    this.currentUserId = user.id;
    this.utilizatorForm.patchValue({
      email: user.email,
      parola: user.parola,
      type: user.rol || user.type || 'CLIENT'
    });
  }

  deleteUtilizator(id: number): void {
    if (confirm('Sunteti sigur ca doriti sa stergeti acest utilizator?')) {
      this.utilizatorService.deleteUtilizator(id).subscribe({
        next: () => this.loadUtilizatori(),
        error: (err) => console.error('Eroare la stergerea utilizatorului', err)
      });
    }
  }

  resetForm(): void {
    this.editMode = false;
    this.currentUserId = null;
    this.utilizatorForm.reset({ type: 'CLIENT' });
  }
}
